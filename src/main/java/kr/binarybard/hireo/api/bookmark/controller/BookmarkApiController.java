package kr.binarybard.hireo.api.bookmark.controller;

import kr.binarybard.hireo.api.bookmark.service.BookmarkService;
import kr.binarybard.hireo.common.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class BookmarkApiController {
	private final BookmarkService bookmarkService;

	@PostMapping({"/api/companies/{id}/bookmarks", "/companies/{id}/bookmarks"})
	public ResponseEntity<Void> bookmarkCompany(
		@CurrentUser User user,
		@PathVariable("id") Long companyId
	) {
		Long bookmarkId = bookmarkService.bookmarkCompany(user, companyId);
		return ResponseEntity.created(URI.create("/api/companies/" + companyId + "/bookmarks/" + bookmarkId)).build();
	}

	@DeleteMapping({"/api/companies/{id}/bookmarks", "/companies/{id}/bookmarks"})
	public ResponseEntity<Object> deleteCompanyBookmark(
		@CurrentUser User user,
		@PathVariable("id") Long companyId
	) {
		bookmarkService.deleteCompanyBookmark(user, companyId);
		return ResponseEntity.noContent().build();
	}
}
