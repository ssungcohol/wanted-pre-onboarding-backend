package com.example.noticeboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.noticeboard.dto.NoticeRequestDto;
import com.example.noticeboard.dto.NoticeResponseDto;
import com.example.noticeboard.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	//게시글 조회
	@GetMapping("/api/notices")
	public List<NoticeResponseDto> getNotice() {
		return noticeService.getNotice();
	}

	//게시글 작성
	@PostMapping("/api/noitce")
	public NoticeResponseDto createNotice (@RequestBody NoticeRequestDto requestDto) {
		return noticeService.createNotice(requestDto);
	}

}
