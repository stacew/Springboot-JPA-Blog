package com.doksam.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.config.auth.PrincipalDetail;
import com.doksam.blog.dto.ResponseDto;
import com.doksam.blog.model.Board;
import com.doksam.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/saveWriteBoard")
	public ResponseDto<Integer> saveWriteBoard(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
		System.out.println("UserApiController : joinProc() 호출 됨.");
		
		if( board.getTitle().isBlank() || board.getTitle().isEmpty() ) 
			return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST, -1);
						
		board.setCount(0);
		board.setUser(principal.getUser());
		boardService.글쓰기(board);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}