package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello";
	}

	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}

	@GetMapping("hello-string")
	//http 패킷의 response body부에 해당 데이터를 직접 넣어주겠다는 의미
	//JSON으로 반환하는 것이 기본.
	//XML로도 반환할 수 있음.
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name; // "hello spring!"
	}

	//Json 방식 (Key-Value) - 자주 사용
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name){
		Hello hello = new Hello();
		hello.setName(name);
		return hello;   // 출력 결과(JSON) : {"name":"spring!!!!!!"}
	}

	//static class로 구성 시, class 내부에 새로운 클래스 정의 및 사용 가능
	static class Hello{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
