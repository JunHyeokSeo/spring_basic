package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//@SpringBootTest : Spring 컨테이너와 테스트를 함께 실행한다
@SpringBootTest
//@Transactional : 테스트 반복을 위한 어노테이션 -  테스트 종료 후 롤백
@Transactional
class MemberServiceIntegrationTest {

	//DI
	//정석 : 생성자 생성하여 매개변수로 Spring Container가 매핑
	//테스트는 편한대로 간략화하여 해도 무방 : @Autowired를 앞에 붙여주는 것으로 DI 구현
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
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

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
}