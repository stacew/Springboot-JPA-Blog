package com.doksam.blog.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doksam.blog.model.Board;
import com.doksam.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public int 글쓰기(Board board) { // title, content
		boardRepository.save(board);
		return 1;
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시글 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		//영속화 완료.
		Board board = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("게시글 아이디를 찾을 수 없습니다.");
		});
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		
		//해당 함수 종료 시, @Transctional 종료. 더티 체킹 - 자동 업데이트
		//그래도 update 해주는게 낫지 않나...?
	}
	

	
}