package com.doksam.blog.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doksam.blog.model.User;
import com.doksam.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	//https://www.youtube.com/watch?v=f5zHFb1BHmY&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=40
	@Transactional
	public int 회원가입(User user) {
		// 인터페이스 vs 서비스
		// 인터페이스에서 transactional 함수를 여러 개 사용하기 안 좋으니 내부에 대해서는 여기서 구현한다.
		// 인터페이스는 서비스로 오는 파라미터의 세팅값 까지만 처리하자.
		// todo. 여기서 repo의 unique 검사 query를 실행한 이후에 error return 방식으로, return 타입은 enum 가능
		// 여기서 try catch 빼고, save 진행하고, 예상하지 못한 exception만 global로 가도록 하자.
		try {
			userRepository.save(user);
			return 1;
		}catch( Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입() : " + e.getMessage());
		}
		return -1;
	}
	
	@Transactional(readOnly = true) //Select 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());		
	}
	
}
