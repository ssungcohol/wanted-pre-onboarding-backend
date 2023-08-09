package com.example.noticeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.noticeboard.dto.NoticeResponseDto;
import com.example.noticeboard.entity.Notice;
import com.example.noticeboard.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;

	//게시글 조회
	public List<NoticeResponseDto> getNotice() {
		List<Notice> noticeList = noticeRepository.findAllByOrderByModifiedAtDesc();
		List<NoticeResponseDto> noticeResponseDtoList = new ArrayList<>();
		for(Notice notice : noticeList) {
			noticeResponseDtoList.add(new NoticeResponseDto(notice));
		}

		return noticeResponseDtoList;
	}

}
