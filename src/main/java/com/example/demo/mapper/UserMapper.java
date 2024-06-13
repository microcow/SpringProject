package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.example.demo.domain.User;

@Mapper
public interface UserMapper {
	 //유저읽기
	   public abstract User readUser(String username);
	   
	   //유저생성
	   public abstract void createUser(User user);

	   // 권한 읽기
	   public List<GrantedAuthority> readAuthorities(String username);

	   // 권한 생성
	   public void createAuthority(User user);
	   
	   // abstract(추상메소드 선언) 생략 가능
}

