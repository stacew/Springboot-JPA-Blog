package com.doksam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/user/infoForm")
	public String createFrom() {		
		return "user/infoForm";		
	}
}