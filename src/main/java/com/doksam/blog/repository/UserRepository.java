package com.doksam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doksam.blog.model.User;


//DAO Data Access Object
// @Repository //생략 가능하다. // 자동으로 bean 등록이 된다.
public interface UserRepository extends JpaRepository<User, Integer>{ //User 테이블을 관리하는 Repo이며, PK는 Interger

	//1.  JPA Naming 전략 : 함수 이름에 따라 쿼리가 만들어짐.
	//SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);
	//2. Native Query 사용 방법 
	@Query(value = "SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery=true)
	User login(String username, String password);
	
}
