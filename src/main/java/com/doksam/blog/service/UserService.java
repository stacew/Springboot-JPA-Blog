package com.doksam.blog.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doksam.blog.model.RoleType;
import com.doksam.blog.model.User;
import com.doksam.blog.repository.UserRepository;

import ch.qos.logback.core.encoder.Encoder;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	// https://www.youtube.com/watch?v=f5zHFb1BHmY&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=40
	@Transactional
	public ServiceResType 회원가입(User user) {
		// 인터페이스 vs 서비스
		// 인터페이스에서 transactional 함수를 여러 번으로 호출하면 안 좋을 것 같아 여기서 구현해야 될 것 같다는 생각..
		// 인터페이스는 서비스로 오는 파라미터의 세팅값 까지만 처리하자.

		if (userRepository.findByUsername(user.getUsername()).isEmpty() == false)
			return ServiceResType.Duplicated_Username;
		if (userRepository.findByEmail(user.getEmail()).isEmpty() == false)
			return ServiceResType.Duplicated_Email;
			
		String rawPassword = user.getPassword(); //ui처리는 https로
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);			
		
		userRepository.save(user);
		return ServiceResType.Success;
	}
}

//@Transactional(readOnly = true) //Select 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
//public User 로그인(User user) {
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());		
//}