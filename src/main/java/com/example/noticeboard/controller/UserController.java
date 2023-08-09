package com.example.noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	@GetMapping("/signup")
	public ModelAndView signupPage() {
		return new ModelAndView("signup");
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

}