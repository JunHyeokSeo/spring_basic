package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
	//실무에서는 동시성 문제 고려 필요
	private static Map<Long, Member > store  = new HashMap<>();
	private static long sequence = 0L;

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		//null이 발생할 수 있는 경우 Optinal로 감싸줌
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return  store.values().stream()
				        .filter(member -> member.getName().equals(name))
				         .findAny();
	}

	//실무에서 List 많이 사용
	@Override
	public List<Member> findAll() {
		//Member 객체들을 ArrayList 형태로 반환
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
	}
}
