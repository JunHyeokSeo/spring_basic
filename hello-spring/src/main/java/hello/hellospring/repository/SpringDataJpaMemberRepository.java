package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 상속 받으면 Spring이 자동으로 구현체를 만들어서 등록해줌
//CRUD 및 단순 조회를 JpaRepository에서 제공해준다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	//JpaRepository는 공통 인터페이스를 제공, Primary key를 사용한 조회, 전체 조회, CRUD는 제공되나
	//직접 작성한 테이블 컬럼 기준 조회 등 공통이 아닌 부분은 직접 정의해야함
    //메소드의 이름을 규칙에 맞게 작성하면, Spring Date JPA는 자동으로 쿼리를 작성한다.
	//findByName => select m from Member m where m.name = ?
	//즉, 인터페이스의 이름만으로 개발이 끝난다
 	@Override
	Optional<Member> findByName(String name);
}
