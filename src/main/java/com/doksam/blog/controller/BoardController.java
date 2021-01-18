package com.doksam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doksam.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// http://localhost:8000
	@GetMapping({ "", "/" }) //
	public String index(Model model, @PageableDefault(size=3,sort="id",direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));		
			
		return "index";
		//@Controller는 리턴할 때 viewResolver가 작동!
		//앞, 뒤로 prefix ,suffix를 붙여주고 해당 페이지로 model의 정보를 보냄.
	}
	
	@GetMapping("/board/{id}")
	public String findBoardById(Model model, @PathVariable int id){
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	//USER 권한 필요
	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
}
