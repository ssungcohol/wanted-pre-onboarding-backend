package com.example.noticeboard.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.noticeboard.dto.LoginRequestDto;
import com.example.noticeboard.dto.SignupRequestDto;
import com.example.noticeboard.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public ModelAndView signupPage() {
		return new ModelAndView("signup");
	}

	@PostMapping("/signup")
	public String signup(@Valid SignupRequestDto signupRequestDto) {
		userService.signup(signupRequestDto);
		return "redirect:/api/user/login";
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@ResponseBody
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
		userService.login(loginRequestDto, response);
		return "로그인 성공";
	}

}
