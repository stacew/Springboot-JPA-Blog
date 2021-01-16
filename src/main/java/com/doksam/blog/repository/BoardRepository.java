package com.doksam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doksam.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> { 

}