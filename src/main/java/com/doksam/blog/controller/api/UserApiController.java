package com.doksam.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.dto.ResponseDto;
import com.doksam.blog.model.RoleType;
import com.doksam.blog.model.User;
import com.doksam.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session; //함수 파라미터로 써도 되고, DI로 써도 된다.
	
	@PostMapping("/api/user/join")
	public ResponseDto<Integer> join(@RequestBody User user){
		System.out.println("UserApiController : join() 호출 됨.");
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);		
		return new ResponseDto<Integer>(HttpStatus.OK, result); //자바 오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
	
	//legacy 로그인 방식.
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		System.out.println("UserApiController : login() 호출 됨.");
		User principal = userService.로그인(user); //pricipal : 접근 주체
		if( principal != null ) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}

}
