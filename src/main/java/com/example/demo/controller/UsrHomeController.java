package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrHomeController {

	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요";
	}
	
//	@RequestMapping("/usr/home/main")
//	public String showMain(HttpServletRequest req) {
//		
//		return "/usr/home/main";
//	}
//
//	@RequestMapping("/")
//	public String showRoot() {
//
//		return "redirect:/usr/home/main";
//	}
}