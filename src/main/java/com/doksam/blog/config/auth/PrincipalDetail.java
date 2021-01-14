package com.doksam.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.doksam.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
// UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장해준다.
public class PrincipalDetail implements UserDetails {
	private User user;// 컴포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠기지 않았는지
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료되지 않았는지
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화가 되어 있는지
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 갖는 권한 목록을 리턴한다. ( 권한이 여러개 있을 수도 있음 )
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();

//		collectors.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				return "ROLE_" + user.getRole(); // "ROLE_USER"
//			}
//		});
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		}); // 함수가 하나 밖에 없어 람다식 사용 가능

		return collectors;
	}

}
