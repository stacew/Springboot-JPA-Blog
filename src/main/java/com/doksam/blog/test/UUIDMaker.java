package com.doksam.blog.test;

import java.util.UUID;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UUIDMaker {
	@Test
	public void MakeUUID() {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);		
	}
}
