package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
   @Autowired
   UserMapper userMapper;
   /* 매퍼 파일에서(UserMapper.xml) namespace를 해당 클래스로 지정하였기에(구현하고있기에) 
   userMapper로 호출할 경우 UserMapper.xml로 매핑된다. */ 
	
   @Override // UserDetailsService 인터페이스는 단 하나의 메서드인 loadUserByUsername을 정의하고 있습니다.
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userMapper.readUser(username);
      user.setAuthorities(getAuthorities(username));

      return user;
   }
   
   @Override
   public Collection<GrantedAuthority> getAuthorities(String username) {
      List<GrantedAuthority> authorities = userMapper.readAuthorities(username);
      return authorities;
   }
   
   @Override
   public void createUser(User user) {
      userMapper.createUser(user);
   }

   @Override
   public void createAuthorities(User user) {
      userMapper.createAuthority(user);
   }

   @Override
   public User readUser(String username) {
      return userMapper.readUser(username);
   }
}
