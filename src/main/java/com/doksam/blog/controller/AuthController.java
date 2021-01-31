package com.doksam.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.doksam.blog.model.AuthType;
import com.doksam.blog.model.User;
import com.doksam.blog.model.kakao.KakaoOAuthToken;
import com.doksam.blog.model.kakao.KakaoProfile;
import com.doksam.blog.service.ServiceResType;
import com.doksam.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, image/** 허용

@Controller
public class AuthController {

	@Value("${doksam.secretUUID}")
	private String doksamSecretUUID;

	// kakao-auth
	@Value("${doksam.auth.kakaoRest.clientKey}")
	private String kakaoClientKey;
	@Value("${doksam.auth.kakaoRest.rediLogin}")
	private String kakaoRediLoginPath;
	@Value("${doksam.auth.kakaoRest.reqToken}")
	private String kakaoReqTokenPath;
	@Value("${doksam.auth.kakaoRest.reqInfo}")
	private String kakaoReqInfoPath;

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/auth/createForm")
	public String createFrom() {
		return "auth/createForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}

	@GetMapping("/auth/kakaoLoginCallback") // 1. 인가 코드 받기. Get
	public String kakaoLoginCallback(String code) { //Response "code"

		////// 2. 토큰 받기. Post
		// Post 방식으로 Key=value 데이터를 카카오로 요청해야 됨.
		// HttpsURLConnection, Retrofit2, RestTemplate 라이브러리 등이 있음.
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientKey);
		params.add("redirect_uri", kakaoRediLoginPath);
		params.add("code", code);

		// HttpHeader HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청 - post 방식 - response<String> 응답 받기.
		ResponseEntity<String> response = rt.exchange(kakaoReqTokenPath, HttpMethod.POST, kakaoTokenRequest,
				String.class);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoOAuthToken kakaoAuthToken = null;
		try {
			kakaoAuthToken = objectMapper.readValue(response.getBody(), KakaoOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		////// 3. 사용자 정보 가져오기. Post ( Get ), Access Token( Admin Key )
		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + kakaoAuthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

		// Http 요청 - post 방식 - response<String> 응답 받기.
		ResponseEntity<String> response2 = rt2.exchange(kakaoReqInfoPath, HttpMethod.POST,
				kakaoProfileRequest, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
		////// 4. 로그인( + 비가입 자 회원 가입 )
		User user = User.builder().auth(AuthType.Kakao).username(kakaoProfile.getId().toString())
				.email(kakaoProfile.getKakao_account().getEmail()).password(doksamSecretUUID).build();
	
		Optional<User> opUser = userService.회원찾기(user);
		if( opUser.isEmpty() ) {
			userService.OAuth회원가입(user);
		}
		
		//회원가입 루틴 진행하면 user.getPassword() 변경되니 doksamSecretUUID 사용.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), doksamSecretUUID));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
}
