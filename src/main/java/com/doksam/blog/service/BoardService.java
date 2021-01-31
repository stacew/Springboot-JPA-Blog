package com.doksam.blog.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doksam.blog.config.auth.PrincipalDetail;
import com.doksam.blog.dto.ReplyCreateRequestDto;
import com.doksam.blog.model.Board;
import com.doksam.blog.model.Reply;
import com.doksam.blog.model.User;
import com.doksam.blog.repository.BoardRepository;
import com.doksam.blog.repository.ReplyRepository;
import com.doksam.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public ServiceResType 생성(PrincipalDetail principal, Board board) { // title, content

		board.setCount(0);
		board.setUser(principal.getUser());

		boardRepository.save(board);
		return ServiceResType.Success;
	}

	@Transactional(readOnly = true)
	public Page<Board> 페이지(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Optional<Board> 가져오기(int id) {
		return boardRepository.findById(id);
	}

	@Transactional
	public ServiceResType 삭제(PrincipalDetail principal, int id) {
		Optional<Board> opBoard = boardRepository.findById(id);
		if (opBoard.isEmpty())
			return ServiceResType.NotFound;

		Board board = opBoard.get();
		if (principal.getUser().getId() != board.getUser().getId())
			return ServiceResType.PrincipalCheckFail;

		boardRepository.deleteById(id);
		return ServiceResType.Success;
	}

	@Transactional
	public ServiceResType 수정(PrincipalDetail principal, int id, Board reqBoard) {
		// 영속화 완료.
		Optional<Board> opBoard = boardRepository.findById(id);
		if (opBoard.isEmpty())
			return ServiceResType.NotFound;

		Board board = opBoard.get();
		if (principal.getUser().getId() != board.getUser().getId())
			return ServiceResType.PrincipalCheckFail;

		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());

		return ServiceResType.Success;
		// 해당 함수 종료 시, @Transctional 종료. 더티 체킹 - 자동 업데이트
		// 그래도 update 해주는게 낫지 않나...?
	}

//	@Transactional
//	public ServiceResType 댓글쓰기(PrincipalDetail principal, int boardId, Reply reply) {
//		Optional<Board> opBoard = boardRepository.findById(boardId);
//		if (opBoard.isEmpty())
//			return ServiceResType.NotFound;
//		
//		reply.setBoard(opBoard.get());
//		reply.setUser(principal.getUser());	
//		replyRepository.save(reply);		
//		return ServiceResType.Success;
//	}
	//RequestDTO(Data Transfer Object) 이용
//	@Transactional
//	public ServiceResType 댓글쓰기(PrincipalDetail principal, ReplyCreateRequestDto replyCreateRequestDto) {
//		Optional<Board> opBoard = boardRepository.findById(replyCreateRequestDto.getBoardId());
//		if (opBoard.isEmpty())
//			return ServiceResType.NotFound;
//
//		Optional<User> opUser = userRepository.findById(principal.getUser().getId());
//		if (opUser.isEmpty())
//			return ServiceResType.NotFound;
//		Reply reply = Reply.builder().user(opUser.get()).board(opBoard.get())
//				.content(replyCreateRequestDto.getContent()).build();
//
//		replyRepository.save(reply);
//		return ServiceResType.Success;
//	}
	//NativeQuery 이용
	@Transactional
	public ServiceResType 댓글쓰기(PrincipalDetail principal, ReplyCreateRequestDto replyCreateRequestDto) {
		replyRepository.nativeCreate(principal.getUser().getId(), replyCreateRequestDto.getBoardId(), replyCreateRequestDto.getContent());
		return ServiceResType.Success;
	}
	
	@Transactional
	public ServiceResType 댓글삭제(PrincipalDetail principal, int replyId) {
	
		Optional<Reply> opReply = replyRepository.findById(replyId);
		if( opReply.isEmpty() )
			return ServiceResType.NotFound;
		
		Reply reply = opReply.get();
		if (principal.getUser().getId() != reply.getUser().getId())
			return ServiceResType.PrincipalCheckFail;
		
		replyRepository.deleteById(replyId);
		return ServiceResType.Success;
	}
}