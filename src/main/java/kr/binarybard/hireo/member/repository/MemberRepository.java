package kr.binarybard.hireo.member.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import kr.binarybard.hireo.member.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long memberId) {
		return em.find(Member.class, memberId);
	}

	public Member findOneByEmail(String email) {
		return em.createQuery("select m from Member m where m.email=:email", Member.class)
			.setParameter("email", email).getResultList().stream().findAny().get();
	}

	public List<Member> findByEmail(String email) {
		return em.createQuery("select m from Member m where m.email =:email", Member.class)
			.setParameter("email", email).getResultList();
	}
}
