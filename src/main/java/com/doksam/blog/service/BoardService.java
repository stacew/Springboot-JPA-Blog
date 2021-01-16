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

	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시글 아이디를 찾을 수 없습니다.");
		});
	}
}