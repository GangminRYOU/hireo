package kr.binarybard.hireo.api.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import kr.binarybard.hireo.api.auth.dto.RefreshTokenRequest;
import kr.binarybard.hireo.api.auth.dto.SignInRequest;
import kr.binarybard.hireo.api.auth.repository.RefreshTokenRepository;
import kr.binarybard.hireo.web.auth.dto.SignUpRequest;
import kr.binarybard.hireo.web.member.repository.MemberRepository;
import kr.binarybard.hireo.web.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	private final String username = "testuser@test.com";
	private final String password = "testPassword123";

	@BeforeEach
	public void setUp() {
		refreshTokenRepository.deleteAll();
		memberRepository.deleteAll();

		SignUpRequest signUpRequest = SignUpRequest.builder()
			.email(username)
			.password(password)
			.build();
		memberService.save(signUpRequest);
	}

	@DisplayName("로그인을 성공적으로 수행한다.")
	@Test
	public void testAuthorize() throws Exception {
		SignInRequest signInRequest = SignInRequest.builder()
			.email(username)
			.password(password)
			.build();

		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(signInRequest)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.access_token").isNotEmpty())
			.andExpect(jsonPath("$.refresh_token").isNotEmpty());
	}

	@Test
	@DisplayName("잘못된 비밀번호로 로그인을 시도하면 실패한다.")
	public void loginFailDueToWrongPassword() throws Exception {
		SignInRequest request = SignInRequest.builder()
			.email(username)
			.password(password + "wrong")
			.build();

		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isForbidden());
	}

	@DisplayName("리프레시 토큰으로 새로운 토큰을 성공적으로 발급한다.")
	@Test
	public void testReissue() throws Exception {
		SignInRequest signInRequest = SignInRequest.builder()
			.email(username)
			.password(password)
			.build();

		MvcResult result = mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(signInRequest)))
			.andReturn();

		String response = result.getResponse().getContentAsString();
		String refreshToken = JsonPath.read(response, "$.refresh_token");

		RefreshTokenRequest refreshTokenRequest = RefreshTokenRequest.builder()
			.refreshToken(refreshToken)
			.build();

		mockMvc.perform(post("/api/auth/reissue")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(refreshTokenRequest)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.access_token").isNotEmpty())
			.andExpect(jsonPath("$.refresh_token").isNotEmpty());
	}
}