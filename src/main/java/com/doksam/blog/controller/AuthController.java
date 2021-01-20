package com.doksam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, image/** 허용

@Controller
public class AuthController {

	@GetMapping("/auth/createForm")
	public String createFrom() {		
		return "auth/createForm";		
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {		
		return "auth/loginForm";		
	}
}