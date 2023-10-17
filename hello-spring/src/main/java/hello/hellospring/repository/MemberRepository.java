package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
	//1. 회원 저장
	Member save(Member member);

	//2. 회원을 id 또는 name을 통해 조회
	//Optional : null을 처리하는 방법 중 하나.
	//반환값을 Optional로 감싸서 처리한다
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);

	//3. 전체 회원 조회
	List<Member> findAll();
}
