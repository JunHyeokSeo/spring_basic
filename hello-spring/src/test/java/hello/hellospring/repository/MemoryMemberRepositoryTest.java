package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();

	//각 테스트는 순서와 관계없이, 서로 의존관계 없이 생성이 되어야 한다.

	//일종의 콜백함수, 각 메소드에 대한 테스트 수행 이후 저장소를 clear한다.
	//해당 함수가 없다면, 저장소가 전체 테스트 기간 중 계속 유지되고,
	//findAll과 findByName 테스트에서 name 값이 동일한 객체가 중복 생성되어 오류가 발생한다.
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");

		repository.save(member);
		Member result = repository.findByName(member.getName()).get();

		// 객체의 저장 및 반환 테스트
		//방법 1.
		// 참이 나온다면, 메모리에 객체를 저장하고 불러오는 작업이 정상적으로 동작한 것
		// System.out.println("result = " + (result == member));

		//방법 2.
		//매번 출력값을 확인하면서 테스트 불가능.
		//junit이 제공하는 메소드를 이용해서 두 객체가 서로 같은지 확인 가능
		//assertEquals( Object expected, Object actual)
		//출력되는 값은 없지만, 테스트 실행 시 정상/오류 표시를 통해 정상동작 여부를 확인할 수 있다
		//Assertions.assertEquals(member, result);

		//방법 3.
		assertThat(member).isEqualTo(result);
	}

	//메소드별 Test 순서는 보장되지 않는다.
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Member result = repository.findByName("spring1").get();

		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
}
