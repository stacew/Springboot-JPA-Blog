package com.doksam.blog.controller.api;

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
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user){
		System.out.println("UserApiController : save() 호출 됨.");

		//나중에 find해서 이름 검색 후 -1 리턴을 여기서 할까 회원가입() 안에서 할까.. 
		//이름 검색 성능 vs transactinal 롤백 성능 
		
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result); //자바 오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user){
	//	System.out.println("UserApiController : login() 호출 됨.");
	//	User principal = userService.로그인(user); //pricipal : 접근 주체
		
	//	return new ResponseDto<Integer>(HttpStatus.OK, 1);
//	}

}
