package com.example.noticeboard.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignupRequestDto {

	// @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}", message = "아이디는 4~10자 영문 소문자, 숫자를 사용하세요.")
	@Email(message = "이메일 형식에 맞지 않습니다.")
	private String email;

	// @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@$!%*#?&]).{8,15}", message = "비밀번호는 8~15자 영문 대 소문자, 숫자를 사용하세요.")
	@Size(message = "비밀번호는 8글자 이상이어야 합니다.", min = 8)
	private String password;

}
