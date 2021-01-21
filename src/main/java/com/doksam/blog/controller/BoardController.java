package com.doksam.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doksam.blog.config.auth.PrincipalDetail;
import com.doksam.blog.model.Board;
import com.doksam.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// http://localhost:8000
	@GetMapping({ "", "/" }) //
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boardPage = boardService.페이지(pageable);
		if (boardPage.hasContent())
			model.addAttribute("boardPage", boardPage);

		return "index";
		// @Controller는 리턴할 때 viewResolver가 작동!
		// 앞, 뒤로 prefix ,suffix를 붙여주고 해당 페이지로 model의 정보를 보냄.
	}

	@GetMapping("/board/{id}")
	public String findBoardById(@PathVariable int id, Model model) {
		Optional<Board> board = boardService.가져오기(id);
		if (board.isEmpty())
			return "exception/notFound";

		model.addAttribute("board", board.get());
		return "board/detail";
	}

	@GetMapping("/board/updateForm/{id}")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal, @PathVariable int id, Model model) {

		Optional<Board> board = boardService.가져오기(id);
		if (board.isEmpty())
			return "exception/notFound";

		if (principal.getUser().getId() != board.get().getUser().getId())
			return "exception/noPermission";

		model.addAttribute("board", board.get());
		return "board/updateForm";
	}

	// USER 권한 필요
	@GetMapping("/board/createForm")
	public String createForm() {
		return "board/createForm";
	}

}
