package com.doksam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doksam.blog.model.User;


//DAO Data Access Object
// @Repository //생략 가능하다. // 자동으로 bean 등록이 된다.
public interface UserRepository extends JpaRepository<User, Integer>{ //User 테이블을 관리하는 Repo이며, PK는 Interger


}
