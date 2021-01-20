package com.doksam.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.doksam.blog.config.auth.PrincipalDetailService;

//시큐리티 어노테이션 3
@Configuration // bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity // 스프링 시큐리티에 활성화된 필터의 설정 변경
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean // IOC가 됨.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private PrincipalDetailService principalDetailService;

	// 시큐리티가 해당 password를 무엇으로 해쉬가 되었는지 encodePWD()
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf 토큰 비 활성화 ( 개발 중 )
				.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll()//해당 주소는 모두 허용
				//.antMatchers("/user").hasRole("USER")
				//.antMatchers("/manager").hasRole("MANAGER")
				//.antMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated() // 다른 요청은 인증이 필요할 때,
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") // redirect...
				.loginProcessingUrl("/auth/login") // 스프링 시큐리티가 해당 주소로 오는 요청을 가로채서 대신해준다.
				.defaultSuccessUrl("/")
				//.successHandler(___________)
				//.failureHandler(________)	//로그인 실패 시 팝업 처리
				.failureUrl("/auth/loginForm");
	}
}
