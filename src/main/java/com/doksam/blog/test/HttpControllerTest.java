package com.doksam.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller
// 사용자가 요청 -> 응답(Data)
//@RestController
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//builder 패턴
		Member m = Member.builder().username("yslee").password("1234").email("abc@naver.com").build();
		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter : " + m.getId());
		return "lombok test 완료1";
	}
	
	//인터넷 브라우저 주소창으로는 무조건 get요청 밖에 할 수 없다.
	//http://localhost:8000/blog/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) { //MessageConverter(스프링부트)
		return "get 요청 : " + m.getId() + ", " + m.getUsername() +", " + m.getPassword() + ", " +m.getEmail();
	}
	
	//http://localhost:8000/blog/http/post(insert)
//	@PostMapping("/http/post")
//	public String postTest(@RequestBody String text) { //text
//		return "post 요청 : " + text;
//	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //json //MessageConverter(스프링부트)
		return "post 요청 : " + m.getId() + ", " + m.getUsername() +", " + m.getPassword() + ", " +m.getEmail();
	}
	
	//http://localhost:8000/blog/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", " + m.getUsername() +", " + m.getPassword() + ", " +m.getEmail();
	}
	//http://localhost:8000/blog/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}	
}
