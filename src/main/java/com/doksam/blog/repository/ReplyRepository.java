package com.doksam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doksam.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	@Modifying
	@Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())", nativeQuery = true)
	public int nativeCreate(int userId, int boardId, String content); //@Modifying int : 업데이트 된 행의 개수를 리턴.
}
