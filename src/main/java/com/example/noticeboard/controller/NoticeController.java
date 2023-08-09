package com.example.noticeboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.noticeboard.dto.NoticeResponseDto;
import com.example.noticeboard.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/api/notices")
	public List<NoticeResponseDto> getNotice() {
		return noticeService.getNotice();
	}

}
