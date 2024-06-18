package com.example.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private DataSource dataSource;
   
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {

//      인증과 권한
      http
      .authorizeRequests()
         .antMatchers("/user/**").authenticated() // "/user/**" 패턴의 URL은 인증된 사용자만 접근 가능
         .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // "/admin/**" 패턴의 URL은 ROLE_ADMIN 권한을 가진 사용자만 접근 가능
         .anyRequest().permitAll() // 그 외 모든 요청은 모든 사용자에게 허용
         .and()
//      폼 로그인 설정
      .formLogin()
         .loginPage("/login") // 로그인 페이지 URL을 "/login"으로 설정합니다.
         .loginProcessingUrl("/loginPro") // 로그인 처리 URL을 "/loginPro"로 설정합니다.
         .defaultSuccessUrl("/", true) // 로그인 성공 후에는 항상 "/"로 리다이렉트합니다.
         .permitAll() // 로그인 페이지는 모든 사용자에게 접근 허용
         .and()
//      로그아웃 설정
      .logout()
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/")
         .invalidateHttpSession(true)
         .deleteCookies("JSESSIONID", "remember-me")
         .and()
//      remember me 설정
      .rememberMe()
         .key("myWeb")
         .rememberMeParameter("remember-me")
         .tokenValiditySeconds(86400)//1day
         .and()
//      exceptionHandling
      .exceptionHandling()
         .accessDeniedPage("/denied")
         .and()
//      session 관리
      .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.NEVER)   
         .invalidSessionUrl("/login")
         .and()
//      csrf   
      .csrf().disable();  // .csrf()는 기본적인 CSRF 보호를 활성화한다. // .disable()은 CSRF 보호를 비활성화한다.
   }
   @Bean
   public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
      db.setDataSource(dataSource);
      return db;
   }
   //security 기본설정
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
   }
}
