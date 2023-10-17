package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

	//service 인스턴스와 service test에서 사용하는 repository가 서로 다르면 안됨.
	//따라서, service 객체 내부에서 사용되는 레포지토리리는 외부에서 넣어줌(DI)
	MemberService memberService;
	MemoryMemberRepository memberRepository;

	//각 테스트가 실행되기 전에 동작. 새로운 service 객체와 repository 객체를 생성한다
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	//test는 한글 사용 가능
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		//when
		memberService.join(member1);

		//() -> memberService.join(member2) 로직을 실행할 때, IllegalStateException Error가 터져야 한다는 의미
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

		//메세지 검증 - assertThrows 반환값으로 에러 객체가 반환되므로 이를 활용하여 검증한다
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

		/*
		try {
			memberService.join(member2);
			//try에서 예외 발생 후, catch로 넘어가지 못한 경우
			fail("예외 처리 필요");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}*/

		//then
	}

	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}