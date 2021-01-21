package com.doksam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/user/updateForm")
	public String updateForm() {		
		return "user/updateForm";		
	}
}