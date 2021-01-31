package com.doksam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doksam.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
