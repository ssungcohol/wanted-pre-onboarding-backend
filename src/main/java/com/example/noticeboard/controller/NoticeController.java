package com.example.noticeboard.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.noticeboard.dto.NoticeMessageDto;
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
	public Page<NoticeResponseDto> getNotices(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return noticeService.getNotices(pageable);
	}

	//게시글 작성
	@PostMapping("/api/notice")
	public NoticeResponseDto createNotice (@RequestBody NoticeRequestDto requestDto, HttpServletRequest request) {
		return noticeService.createNotice(requestDto, request);
	}

	// 특정 게시글 조회
	@GetMapping("/api/notice/{id}")
	public NoticeResponseDto getNotice(@PathVariable Long id) {
		return noticeService.getNotice(id);
	}

	// 게시글 수정
	@PutMapping("/api/notice/{id}")
	public NoticeResponseDto updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto, HttpServletRequest request) {
		return noticeService.updateNotice(id, requestDto, request);
	}

	// 게시글 삭제
	@DeleteMapping("/api/notice/{id}")
	public NoticeMessageDto deleteNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto, HttpServletRequest request) {
		return noticeService.deleteNotice(id, requestDto, request);
	}
}
