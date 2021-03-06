package com.doksam.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doksam.blog.model.User;
import com.doksam.blog.repository.UserRepository;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// 시큐리티 로그인 시 username과 password 변수 2개를 가로 채는데, password 부분 틀린지는 알아서 체크 됨.
	// username이 DB에 있는지 확인만 해주면 됨.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> {
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
		});

		return new PrincipalDetail(user); // 시큐리티 세션에 유저 정보가 저장이 됨.
	}

}
