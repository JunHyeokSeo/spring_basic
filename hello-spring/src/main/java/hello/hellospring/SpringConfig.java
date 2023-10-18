package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//Spring Container 실행될 때, @Configureation 어노테이션 발견되면, 객체 생성하여 Spring Bean에 등록
@Configuration
public class SpringConfig {

	private final MemberRepository memberRepository;

	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

	//AOP는 Bean에 직접 등록하는 것이 좋음
	//Service, Repository 등 정형화된 형태는 어노테이션으로 많이 사용하지만,
	//AOP등은 Bean에 직접 작성하여 AOP를 사용중임을 알기 쉽게 하는 것이 좋음
	//Bean에 등록하지 않으려면 @Component 사용
//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
}
