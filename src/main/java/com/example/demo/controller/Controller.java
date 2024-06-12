package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;

@org.springframework.stereotype.Controller
public class Controller {
	
	/*  @RequestMapping("/")
   		public String home() {
       		return "/index";
   		} */
	
	@Autowired BoardService boardservice;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Board> list = boardservice.selectBoardList();
		model.addAttribute("list", list);
		return "/index";
	}
}
// 어떻게 로그인화면이 생성되었고 ID는 왜 user이고 security password는 무엇인가