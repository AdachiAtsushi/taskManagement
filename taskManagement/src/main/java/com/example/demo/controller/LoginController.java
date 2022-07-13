package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;

@Controller
@RequestMapping("/task")
public class LoginController {
	
	/**
	 * ÉçÉOÉCÉìâÊñ èâä˙ï\é¶
	 */
	@GetMapping("/login")
	public String loginDisplay(Model model) {
		LoginForm loginForm = new LoginForm();
		
		model.addAttribute("loginForm", loginForm);
		
		return "task/login";
	}
}
