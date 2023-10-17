package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Spring 컨테이너가 생성될 때, Controller 어노테이션이 붙은 Controller 클래스의 인스턴스를 생성하여 가지고 있는다.
//이를 통해 이후 컨트롤러의 기능을 사용할 수 있다.
//위와 같은 방식을 컴포넌트 스캔이라고 한다.
//@Controller, @Service, @Repository 모두 특수화된 컴포넌트이다.
//위 방법을 사용하면 Spring 컨테이너 생성 시, 각 클래스의 객체를 생성하여 가지고 있다가 매핑한다.
//@Autowired는 자동으로 매개변수와 컴포넌트를 매핑해주는 어노테이션이다.
@Controller
public class MemberController {
	private final MemberService memberService;

	//Autowired : Spring 컨테이너가 뜨면서 객체가 생성될 때, SpringBean에 등록되어 있는 'memberService'를 해당 생성자의 매개변수와 매핑해준다.
	//이제부터 우리는 memberService 객체 하나를 여러 클래스에서 공유하여 사용할 수 있다.
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	//데이터 조회 : Get
	@GetMapping("members/new")
	public String createForm() {
		return "members/createMemberForm";
	}

	// 데이터 등록 : Post
	@PostMapping("members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member);
		return "redirect:/";
	}

	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members =  memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
