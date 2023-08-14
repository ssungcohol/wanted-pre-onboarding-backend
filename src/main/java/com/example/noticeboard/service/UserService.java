package com.example.noticeboard.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.noticeboard.dto.LoginRequestDto;
import com.example.noticeboard.dto.SignupRequestDto;
import com.example.noticeboard.entity.User;
import com.example.noticeboard.jwt.JwtUtil;
import com.example.noticeboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final JwtUtil jwtUtil;

	@Transactional
	public void signup(SignupRequestDto signupRequestDto) {
		String email = signupRequestDto.getEmail();
		String password = signupRequestDto.getPassword();

		// 회원 중복 여부 확인
		Optional<User> found = userRepository.findByEmail(email);
		if (found.isPresent()) {
			throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
		}

		User user = new User(email, password);
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
		String email = loginRequestDto.getEmail();
		String password = loginRequestDto.getPassword();

		// 이메일 확인
		User user =  userRepository.findByEmail(email).orElseThrow(
			() -> new IllegalArgumentException("등록된 사용자가 없습니다.")
		);

		// 비밀번호 확인
		if(!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail()));
	}

}
