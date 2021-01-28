package com.doksam.blog.model.kakao;

import lombok.Data;

@Data
public class KakaoOAuthToken {
	private String token_type;
	private String access_token;	
	private int expires_in;
	private String refresh_token;
	private int refresh_token_expires_in;	
	private String scope;	
}
