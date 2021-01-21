package com.doksam.blog.controller.api;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.config.auth.PrincipalDetail;
import com.doksam.blog.dto.ResponseDto;
import com.doksam.blog.model.User;
import com.doksam.blog.service.ServiceResType;
import com.doksam.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PutMapping("/api/user/update")
	public ResponseDto update(@AuthenticationPrincipal PrincipalDetail principal, String originalPassword,
			String newPassword, String email) {

		if( originalPassword.isEmpty() )
			return new ResponseDto(HttpStatus.BAD_REQUEST, "origianl password is empty.");
		else if( newPassword.isEmpty() )
			return new ResponseDto(HttpStatus.BAD_REQUEST, "new password is empty.");
		else if( email.isEmpty() )
			return new ResponseDto(HttpStatus.BAD_REQUEST, "email is empty.");
		//else if ( password & email 규칙 )
		
		// service
		ServiceResType res = userService.회원수정(principal, originalPassword, newPassword, email);
		if (res == ServiceResType.NotFound)
			return new ResponseDto(HttpStatus.NOT_FOUND, "not found id.");
		else if (res == ServiceResType.PrincipalCheckFail)
			return new ResponseDto(HttpStatus.UNAUTHORIZED, "no Permission.");
		else if( res == ServiceResType.WrongPassword)
			return new ResponseDto(HttpStatus.BAD_REQUEST, "wrong password.");

		// session : 서비스 트랜잭션이 종료되면 DB값이 변경되었지만, 세션값은 변경되지 않았기 때문에 직접 변경해줘야 함.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(principal.getUsername(), newPassword));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto(HttpStatus.OK, "");
	}

}
