package kr.binarybard.hireo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.binarybard.hireo.domain.Member;
import kr.binarybard.hireo.domain.MemberDto;
import kr.binarybard.hireo.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception {
		//given
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail("abc@naver.com");
		memberDto.setPassword("1234");
		memberDto.setRole("FREELANCER");
		memberDto.setName("fff");
		//when
		Long joinedId = memberService.join(memberDto);
		//then
		Member member = memberService.findMember(joinedId);
		Assertions.assertThat(member.getId()).isEqualTo(joinedId);

	}

	@Test
	public void 이메일조회() throws Exception {
		//given
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail("abc@naver.com");
		memberDto.setPassword("1234");
		memberDto.setRole("FREELANCER");
		memberDto.setName("fff");
		Long joinedId = memberService.join(memberDto);
		//when
		Member member = memberService.findMemberByEmail(memberDto.getEmail());
		//then
		Assertions.assertThat(joinedId).isEqualTo(member.getId());
	}

	public MemberDto getMemberDto(String email, String password, String name, String role) {
		MemberDto memberDto = new MemberDto();
		memberDto.setName(name);
		memberDto.setEmail(email);
		memberDto.setRole(role);
		memberDto.setPassword(password);
		return memberDto;
	}
}
