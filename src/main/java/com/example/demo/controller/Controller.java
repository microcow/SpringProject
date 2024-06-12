package com.example.demo.controller;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
   @RequestMapping("/")
   public String home() {
       return "/index";
   }
}
// 어떻게 로그인화면이 생성되었고 ID는 왜 user이고 security password는 무엇인가