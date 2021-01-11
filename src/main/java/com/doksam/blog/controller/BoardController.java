package com.doksam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//http://localhost:8000/blog, http://localhost:8000/blog/
	@GetMapping({"","/"}) //
	public String index() {
		return "index";//WEB-INF/views/index.jsp
	}
}
