package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

//service는 비즈니스 의존적으로 설계
//@Service : 특수화 된 @Component
//@Service
public class MemberService {
	private final MemberRepository memberRepository;

//	@Autowired
	//MemberService 입장에서 Repository를 외부에서 자동으로 넣어줌 => 의존성 주입(Dependency Injection, DI)
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	//회원가입
	public Long join(Member member) {
		//같은 이름이 있는 중복 회원X
		//방법 1.
		//		 Optional<Member> result = memberRepository.findByName(member.getName());

		//Optional이라 가능한 예외처리.
		//ifPresent : null이 아니라 값이 있으면 동작
		//		result.ifPresent(m -> {
		//			throw new IllegalStateException("이미 존재하는 회원입니다.");
		//		});

		//방법 2.
		//result로 별도로 받지 않고 바로 예외처리
		//		memberRepository.findByName(member.getName()).ifPresent(m -> {
		//			throw new IllegalStateException("이미 존재하는 회원입니다.");
		//		});
		//		memberRepository.save(member);

		//방법 3 - 별도의 Method 생성 ()
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}

	//전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
