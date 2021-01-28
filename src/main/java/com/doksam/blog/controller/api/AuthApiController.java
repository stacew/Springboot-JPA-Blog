package com.doksam.blog.controller.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.doksam.blog.dto.ResponseDto;
import com.doksam.blog.model.AuthType;
import com.doksam.blog.model.User;
import com.doksam.blog.model.kakao.KakaoOAuthToken;
import com.doksam.blog.model.kakao.KakaoProfile;
import com.doksam.blog.service.ServiceResType;
import com.doksam.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AuthApiController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/api/create")
	public ResponseDto create(@RequestBody User user) {
		// @Column(nullable=false) 만으로는 DB에 emptyString 방지가 불가능 하기 때문에 추가
		if (user.getUsername().isBlank() || user.getUsername().isEmpty())
			return new ResponseDto(HttpStatus.BAD_REQUEST, "username is empty.");
		else if (user.getPassword().isBlank() || user.getPassword().isEmpty())
			return new ResponseDto(HttpStatus.BAD_REQUEST, "password is empty.");
		else if (user.getEmail().isBlank() || user.getEmail().isEmpty())
			return new ResponseDto(HttpStatus.BAD_REQUEST, "email is empty.");
		// else if ( username & password & email 규칙 )

		// service
		ServiceResType res = userService.회원가입(user);
		if (res == ServiceResType.Duplicated_Username)
			return new ResponseDto(HttpStatus.CONFLICT, "unable username.");
		else if (res == ServiceResType.Duplicated_Email)
			return new ResponseDto(HttpStatus.CONFLICT, "unable email.");

		return new ResponseDto(HttpStatus.OK, ""); // 자바 오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}

	
}

////legacy 로그인 방식.
//@Autowired
//private HttpSession session; //함수 파라미터로 써도 되고, DI로 써도 된다.
////ex) public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//@PostMapping("/api/user/login")
//public ResponseDto<Integer> login(@RequestBody User user){
//	System.out.println("UserApiController : login() 호출 됨.");
//	User principal = userService.로그인(user); //pricipal : 접근 주체
//	if( principal != null ) {
//		session.setAttribute("principal", principal);
//	}
//	return new ResponseDto<Integer>(HttpStatus.OK, 1);
//}
