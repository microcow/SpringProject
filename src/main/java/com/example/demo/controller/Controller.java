package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Board;
import com.example.demo.domain.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.UserService;

@org.springframework.stereotype.Controller
public class Controller {
	
	/*  @RequestMapping("/")
   		public String home() {
       		return "/index";
   		} */
	
	@Autowired BoardService boardservice;
	@Autowired UserService userservice;
	// Authwired : 의존성 주입. UserService 타입의 빈(bean)을 주입받겠다는 의미
	// 해당 인터페이스를 구현한 클래스의 인스턴스를 찾아서 주입합니다. 예를 들어, UserServiceImpl이 UserService를 구현한 경우, UserServiceImpl의 인스턴스를 주입할 수 있습니다.	
	
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Board> list = boardservice.selectBoardList();
		model.addAttribute("list", list);
		return "/index";
	}
	@RequestMapping("/beforeSignUp") 
	public String beforeSignUp() {
		return "/signup";
	}
	
	@RequestMapping("/signup")
	public String signup(@ModelAttribute User user) {
		// request.getParameter로 받지 않음
		// Spring에서 알아서 입력받은 내용을 User클래스로 인식하여 값을 세팅(setter를 호출해서)함.
		/* 만약, <input type="text" name="username" placeholder="id 입력"> 라는 input타입 정보를 입력받고 해당 매서드가 받았을 경우
		입력받은 내용을 자동으로 user클래스의 SetUsername메서드를 Spring에서 호출하고 값을 세팅함*/
		/* @ModelAttribute: 스프링이 아닌 서블릿이었을 경우, request.getParameter("username")로 일일히 정보를 받은 후 User 클래스 인스턴스를 생성해서 받은 정보를 저장해주어야하지만,
		   여기선 알아서 User 클래스로 파라미터를 받으면 따로 request.getParameter하지 않아도 받은 정보가 User클래스의 user 인스턴스에 바인딩된다. */
		
		//비밀번호 암호화
	      String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
	      // BCryptPasswordEncoder클래스의 encode메서드는 주어진 정보를 BCrypt 알고리즘으로 암호화함
	      
	      //유저 데이터 세팅
	      user.setPassword(encodedPassword);
	      user.setAccountNonExpired(true);
	      // 계정이 만료되지 않았음(true)을 설정합니다. 즉, 계정의 유효 기간이 지나지 않았음을 나타냅니다.
	      user.setEnabled(true);
	      // 계정이 활성화되었음(true)을 설정합니다. 활성화된 계정은 사용자가 로그인할 수 있는 상태입니다.
	      user.setAccountNonLocked(true);
	      // 계정이 잠기지 않았음(true)을 설정합니다. 계정이 잠긴 상태에서는 로그인이 제한될 수 있습니다.
	      user.setCredentialsNonExpired(true);
	      // 사용자의 인증 자격이 만료되지 않았음(true)을 설정합니다. 예를 들어, 비밀번호의 유효 기간이 지나지 않았음을 나타냅니다.
	      user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER")); 
	      // 사용자가 가진 권한을 설정
	      // AuthorityUtils.createAuthorityList("ROLE_USER")는 ROLE_USER 권한을 가진 SimpleGrantedAuthority '객체'를 생성하여 '리스트'로 반환   
	      
	      //유저 생성
	      userservice.createUser(user); // 의존성 주입에 의해 userServiceilpl의 creareUser메서드가 호출된다.
	      //유저 권한 생성
	      userservice.createAuthorities(user);
	    return "/login";
	}
	
	@RequestMapping(value="/login")
	public String beforeLogin(Model model) {
		return "/login";
	}
	@Secured({"ROLE_ADMIN"})
	   @RequestMapping(value="/admin")
	   public String admin(Model model) {
	      return "/admin";
	   }
	   
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/user/info")
	public String userInfo(Model model) {
		
		return "/user_info";
	}
	
	@RequestMapping(value="/denied")
	public String denied(Model model) {
		return "/denied";
	}
}