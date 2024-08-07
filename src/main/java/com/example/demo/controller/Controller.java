package com.example.demo.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Board;
import com.example.demo.domain.Reply;
import com.example.demo.domain.Search;
import com.example.demo.domain.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.UserService;

@org.springframework.stereotype.Controller
public class Controller {
	
	/*  @RequestMapping("/")
   		public String home() {
       		return "/index";
   		} */
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardservice;
	@Autowired UserService userservice;
	@Autowired PasswordEncoder passwordEncoder;
	/* Authwired : 의존성 주입. UserService 타입의 빈(bean)을 주입받겠다는 의미
	   즉, 빈으로 등록된 것만 주입받을 수 있음.
	*/
	
	// 해당 인터페이스를 구현한 클래스의 인스턴스를 찾아서 주입합니다. 예를 들어, UserServiceImpl이 UserService를 구현한 경우, UserServiceImpl의 인스턴스를 주입할 수 있습니다.	
	
	@RequestMapping("/")
	public String home(Model model) { // Model은 서블릿의 request와 같은 동작을한다.
		
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
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
	      String encodedPassword = passwordEncoder.encode(user.getPassword());
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
	
	@RequestMapping(value="/login") // value는 생략가능하다
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
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/createboard")
	public String createBoard(@RequestParam(value = "bId", required = false) Integer bId, Model model) {
								//★ required = false : bId의 값이 있을때만 파라미터로 받음
		
		if (bId != null) { //답글일 경우 원글 전달(bId가 Integer타입이므로 != 0이 아닌 != null)
			Board momBoard = boardservice.selectBoard((int)bId);
			model.addAttribute("momBoard" ,momBoard); 
		}
		
		return "/createboard";
	}
	
	
	/*
	// jsp에서 입력받은 값을 불러와 저장하는법 1 (직접 바인딩하는 법) 
	 @Secured({"ROLE_USER"})
	    @RequestMapping(value = "/insertboard", method = RequestMethod.POST)
	    public String insertBoard(
	            @RequestParam("bTitle") String title,
	            @RequestParam("bContent") String content,
	            @RequestParam("bWriter") String writer) {
		
		 Board board = new Board();
	        board.setbTitle(title);
	        board.setbContent(content);
	        board.setbWriter(writer);
		 
		return "/createboard";		
	}
	*/

	// jsp에서 입력받은 값을 불러와 저장하는법 2 (Spring이 자동으로 저장하는 법) 
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/insertboard")
	public String insertBoard(Board board) {
		
		/*
		int insertBoardID = boardservice.createBoard(board); // 방금 db에 저장된 데이터의 자동생성키 가져오는 법 1(트랜잭션 어노테이션 사용)
		board.setbId(insertBoardID); // bID값 셋팅
		*/
		boardservice.insertBoard(board); // 방금 db에 저장된 데이터의 자동생성키 가져오는 법 2(프로퍼티 키 키워드 사용, bID값 자동 세팅)

		
		if (board.getP_board() != 0) {// 답글일 경우 부모 bId 값을 p_board값으로 저장
		board.setP_board(board.getP_board()); // 답글일 경우 부모 p_board 값을 p_board값으로 저장
		board.setDepth(board.getDepth()+1); // 답글일 경우 부모 depth값 +1
		boardservice.updateGrpord(board); // 같은 p_board를 가진 게시글 부모 grpord값보다 큰 grpord를 가진 게시글의 grpord값 +1씩 더하기
		board.setGrpord(board.getGrpord()+1); // +1씩 더하면 부모 grpord값 +1의 값은 비게되니까 본인이 부모 grpord값 +1로 설정
		board.setbTitle("ㄴ" + board.getbTitle()); // 답글일 경우 제목 앞에 ㄴ 추가
		}
		else {// 원글일 경우 자신의 bId값을 p_board값으로 저장, depth 1로 설정, grpord 0으로 설정
			board.setP_board(board.getbId());
			board.setDepth(1);
			board.setGrpord(0);
		}
		
		boardservice.updateData(board);
		
		
		return "/index";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/boardlist")
	public String boardList(Model model) {
		List<Board> list = boardservice.selectBoardList();
		model.addAttribute("list", list); 
		// Model 인터페이스의 addAttribute메서드가 서블릿의 request setAttribute와 같은 동작 (jsp로 값을 전달하기 위해 사용)
		
		return "/boardlist";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/boarddetail")
	public String boarddetail(@RequestParam("bId") int bId, Model model) {
		// get방식으로 bId값을 받고 있음
		Board board = boardservice.selectBoard(bId);
		List<Reply> replyList = boardservice.selectReplyList(bId);
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		
		return "/boarddetail";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/correctionboard")
	public String correctionboard(@RequestParam("bId") int bId, @RequestParam("username") String username, Model model) {
		// 글 수정하려는 작성자가 맞는지 확인필요
		Board board = boardservice.selectBoard(bId);
		
		if (board.getbWriter().equals(username)) {
			// 접속된 유저와 수정하려는 게시글 작성자 이름이 일치하는지 확인
			model.addAttribute("board", board);
			return "/correctionboard";
		}
		else return "/wrongapproach";
		
		
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/updateboard")
	public String updateboard(@RequestParam("bId") int bId,
							 @RequestParam("bTitle") String bTitle,
							 @RequestParam("bContent") String bContent,
							 Model model) {
		Board board = boardservice.selectBoard(bId);
		board.setbTitle(bTitle);
		board.setbContent(bContent);
		boardservice.updateBoard(board);
		model.addAttribute("board", board);	
		
		return "/boarddetail";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/deleteboard")
	public String deleteboard(@RequestParam("username") String username,
				   			  @RequestParam("bId") int bId,
							  Model model) {
		
		Board board = boardservice.selectBoard(bId);		
		if (board.getbWriter().equals(username)) { // 현재 접속자와 글 작성자 명이 일치하는지 확인
			boardservice.deleteBoard(bId);
			return "/deletecomplete";
		}
		else return "/wrongapproach";
		
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/reply")
	public String reply(@RequestParam("b_id") int bId,
				   			  @RequestParam("r_content") String r_content,
				   			  @RequestParam("r_writer") String r_writer,
				   			  @RequestParam(value = "p_rp", required = false) Integer p_rp,
							  @RequestParam(value = "r_depth", required = false) Integer r_depth,
							  @RequestParam(value = "r_grpord", required = false) Integer r_grpord,
							  Model model) {
		
		Reply reply = new Reply();
		reply.setB_id(bId);
		reply.setR_content(r_content);
		reply.setR_writer(r_writer);
		boardservice.insertReply(reply); //글 저장(원 댓글이든 대댓글이든 상관없이)
		
		if (p_rp != null) { // 대댓글일 경우 p_rp, r_depth, r_grpord 값 세팅
			reply.setP_rp(p_rp);
			reply.setR_depth(r_depth + 1);
			reply.setR_grpord(r_grpord); // 부모 댓글 grpord로 셋팅
			boardservice.updateReplyGrpord(reply); // grpord 업데이트 메서드 실행 (부모 grpord보다 높으면 +1) 
			reply.setR_grpord(r_grpord+1); // 나는 빈자리인 부모 grpord +1 값으로 세팅
			boardservice.updateReply(reply);
		}
		else { // 원글일 경우 p_rp, r_depth, r_grpord 값 세팅
			reply.setP_rp(reply.getR_id());
			reply.setR_depth(1);
			reply.setR_grpord(0);
			boardservice.updateReply(reply);
		}
		
		List<Reply> replyList = boardservice.selectReplyList(bId);
		model.addAttribute("replyList", replyList);
		

		return "/ajax_reply";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/changeReply")
	public String changeReply(Reply reply, Model model) {
		boardservice.changeReply(reply);
		List<Reply> replyList = boardservice.selectReplyList(reply.getB_id());
		model.addAttribute(replyList);
		
		return "/ajax_reply";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/deleteReply")
	public String deleteReply(Reply reply, Model model) {
		boardservice.deleteReply(reply);
		List<Reply> replyList = boardservice.selectReplyList(reply.getB_id());
		model.addAttribute(replyList);
		
		return "/ajax_reply";
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/searchBoard")
	public String searchBoard(Search search, Model model) {
		System.out.println(search.getContent());
		System.out.println(search.getSearch());
		List<Board> searchResult = boardservice.searchBoard(search);
		
		model.addAttribute("list", searchResult); //이미 사용되고있던 jsp로 전달하기에 전달하는 변수이름을 상단의 boardList 메서드와 동일하게 list로 통일
		
		return "/boardlist";
	}
	
	
}