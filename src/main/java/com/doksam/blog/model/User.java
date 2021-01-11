package com.doksam.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성된다. //ORM : JAVA(또는 다른 언어) Object -> 테이블로 매핑해주는 기술
//@DynamicInsert
public class User {

	@Id//Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;// 오라클(시퀀스), MySql(auto_increment)
	
	@Column(nullable = false, length = 30, unique=true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 => 해시(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50) 
	private String email;
	
	//@ColumnDefault("user")
	//private StringRoleType role;
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER
		
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
	
}
