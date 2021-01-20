package com.doksam.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.config.auth.PrincipalDetail;
import com.doksam.blog.dto.ResponseDto;
import com.doksam.blog.model.Board;
import com.doksam.blog.service.BoardService;
import com.doksam.blog.service.ServiceResType;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board/create")
	public ResponseDto create(@AuthenticationPrincipal PrincipalDetail principal, @RequestBody Board board) {

		if (board.getTitle().isBlank() || board.getTitle().isEmpty())
			return new ResponseDto(HttpStatus.BAD_REQUEST, "title is empty.");

		//service
		boardService.생성(principal, board);
		return new ResponseDto(HttpStatus.OK, "");
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto deleteById(@AuthenticationPrincipal PrincipalDetail principal, @PathVariable int id) {

		//service
		ServiceResType res = boardService.삭제(principal, id);
		if (res == ServiceResType.NotFound)
			return new ResponseDto(HttpStatus.NOT_FOUND, "not found id");
		else if (res == ServiceResType.Principal)
			return new ResponseDto(HttpStatus.UNAUTHORIZED, "principal");

		return new ResponseDto(HttpStatus.OK, "");
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto update(@AuthenticationPrincipal PrincipalDetail principal, @PathVariable int id,
			@RequestBody Board board) {

		//service
		ServiceResType res = boardService.수정(principal, id, board);
		if (res == ServiceResType.NotFound)
			return new ResponseDto(HttpStatus.NOT_FOUND, "not found id");
		else if (res == ServiceResType.Principal)
			return new ResponseDto(HttpStatus.UNAUTHORIZED, "principal");

		return new ResponseDto(HttpStatus.OK, "");
	}

}