package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect : AOP 필수 어노테이션
@Aspect
@Component
public class TimeTraceAop {
	//hello.hellospring 패키지 하위에 전부 적용
	@Around("execution(* hello.hellospring..*(..))")
	public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
		//joinPoint : 메소드 호출할 때마다 인터셉트가 걸린다. ProceedingJoinPoint 객체를 통해 해당 시점에서 원하는 동작을 수행할 수 있다.
		long start = System.currentTimeMillis();
		System.out.println("START : " + joinPoint.toString());
		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}
