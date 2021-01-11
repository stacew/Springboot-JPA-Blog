package com.doksam.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doksam.blog.model.User;
import com.doksam.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//@Transactional //여기 구조 설계가 좀 필요할 수도?
	//https://www.youtube.com/watch?v=f5zHFb1BHmY&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=40
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		}catch( Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입() : " + e.getMessage());
		}
		return -1;
	}
	
	//@Transactional
	//public User 로그인(User user) {
		//return 
		
	//}
	
}
