package com.doksam.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.model.Board;
import com.doksam.blog.model.Reply;
import com.doksam.blog.repository.BoardRepository;
import com.doksam.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get(); //jackson 라이브러리(Object를 json으로 리턴) => 모델의 getter를 호출
	}
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
	
}
