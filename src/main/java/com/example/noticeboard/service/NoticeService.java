package com.example.noticeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.noticeboard.dto.NoticeMessageDto;
import com.example.noticeboard.dto.NoticeRequestDto;
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


	//게시글 작성
	@Transactional
	public NoticeResponseDto createNotice(NoticeRequestDto requestDto) {
		Notice notice = new Notice(requestDto);
		noticeRepository.save(notice);
		NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
		return noticeResponseDto;
	}

	//특정 게시글 조회
	@Transactional
	public NoticeResponseDto getNotice(Long id) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
		);

		NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
		return noticeResponseDto;
	}

	//게시글 수정
	@Transactional
	public NoticeResponseDto updateNotice(Long id, NoticeRequestDto requestDto) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
		);

		notice.update(requestDto);

		return new NoticeResponseDto(notice);
	}

	//게시글 삭제
	@Transactional
	public NoticeMessageDto deleteNotice(Long id, NoticeRequestDto requestDto) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
		);

		noticeRepository.deleteById(id);
		return new NoticeMessageDto("게시물이 정상적으로 삭제되었습니다.");
	}
}
