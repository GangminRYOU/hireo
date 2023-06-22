package kr.binarybard.hireo.api.bookmark.service;

import jakarta.transaction.Transactional;
import kr.binarybard.hireo.api.bookmark.domain.Bookmark;
import kr.binarybard.hireo.api.bookmark.repository.BookmarkRepository;
import kr.binarybard.hireo.common.exceptions.ErrorCode;
import kr.binarybard.hireo.common.exceptions.InvalidValueException;
import kr.binarybard.hireo.web.company.repository.CompanyRepository;
import kr.binarybard.hireo.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;
	private final CompanyRepository companyRepository;
	private final MemberRepository memberRepository;

	public Long bookmarkCompany(User user, Long companyId) {
		var company = companyRepository.findByIdOrThrow(companyId);
		var loggedInUser = memberRepository.findByEmailOrThrow(user.getUsername());
		var bookmark = Bookmark.builder()
			.company(company)
			.member(loggedInUser)
			.build();

		try {
			return bookmarkRepository.save(bookmark).getId();
		} catch (DataIntegrityViolationException ex) {
			throw new InvalidValueException(ErrorCode.COMPANY_BOOKMARK_ALREADY_EXISTS,
				String.format("회사 ID: %d, 멤버 ID: %d", companyId, loggedInUser.getId()));
		}
	}

	public void deleteCompanyBookmark(User user, Long companyId) {
		var loggedInUser = memberRepository.findByEmailOrThrow(user.getUsername());
		var foundBookmark = bookmarkRepository.findByMemberIdAndCompanyIdOrThrow(loggedInUser.getId(), companyId);
		bookmarkRepository.delete(foundBookmark);
	}
}
