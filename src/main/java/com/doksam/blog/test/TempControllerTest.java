package com.doksam.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	/*
	// http://localhost:8000/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		//파일리턴 기본 경로 : src/main/resources/static
		//리턴명 : /home.html
		//풀 경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		//풀 경로 : src/main/resources/static/a.png
		return "/testImg.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views/
	    // suffix: .jsp
		// 풀 경로 : src/main/webapp/WEB-INF/views/test.jsp
		return "test";
	}
	*/
}
