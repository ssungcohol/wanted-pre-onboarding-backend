package com.example.noticeboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.noticeboard.dto.NoticeMessageDto;
import com.example.noticeboard.dto.NoticeRequestDto;
import com.example.noticeboard.dto.NoticeResponseDto;
import com.example.noticeboard.entity.Notice;
import com.example.noticeboard.entity.User;
import com.example.noticeboard.jwt.JwtUtil;
import com.example.noticeboard.repository.NoticeRepository;
import com.example.noticeboard.repository.UserRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;

	private final JwtUtil jwtUtil;

	private final UserRepository userRepository;

	//게시글 조회
	@Transactional(readOnly = true)
	public List<NoticeResponseDto> getNotices() {
		List<Notice> noticeList = noticeRepository.findAllByOrderByModifiedAtDesc();
		List<NoticeResponseDto> noticeResponseDtoList = new ArrayList<>();
		for (Notice notice : noticeList) {
			noticeResponseDtoList.add(new NoticeResponseDto(notice));
		}

		return noticeResponseDtoList;
	}

	//게시글 작성
	// @Transactional
	// public NoticeResponseDto createNotice(NoticeRequestDto requestDto, User user) {
	// 	Notice notice = new Notice(requestDto, user);
	// 	noticeRepository.save(notice);
	// 	NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
	// 	return noticeResponseDto;
	// }

	@Transactional
	public NoticeResponseDto createNotice(NoticeRequestDto requestDto, HttpServletRequest request) {
		// Request에서 Token 가져오기
		String token = jwtUtil.resolveToken(request);
		Claims claims;

		// 토큰이 있는 경우에만 게시글 추가 가능
		if (token != null) {
			if (jwtUtil.validateToken(token)) {
				// 토큰에서 사용자 정보 가져오기
				claims = jwtUtil.getUserInfoFromToken(token);
			} else {
				throw new IllegalArgumentException("Token Error");
			}

			// 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
			User user = userRepository.findByEmail(claims.getSubject()).orElseThrow(
				() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
			);

			// 요청받은 DTO 로 DB에 저장할 객체 만들기
			Notice notice = noticeRepository.saveAndFlush(new Notice(requestDto, user));

			return new NoticeResponseDto(notice);

		} else {
			return null;
		}
	}

	//특정 게시글 조회
	@Transactional
	public NoticeResponseDto getNotice(Long id) {
		Notice notice = noticeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
		);

		NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
		return noticeResponseDto;
	}

	//게시글 수정
	// @Transactional
	// public NoticeResponseDto updateNotice(Long id, NoticeRequestDto requestDto) {
	// 	Notice notice = noticeRepository.findById(id).orElseThrow(
	// 		() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
	// 	);
	//
	// 	notice.update(requestDto);
	//
	// 	return new NoticeResponseDto(notice);
	// }

	@Transactional
	public NoticeResponseDto updateNotice(Long id, NoticeRequestDto requestDto, HttpServletRequest request) {
		// NoticeResponseDto 타입! Request에서 Token 가져오기
		String token = jwtUtil.resolveToken(request);
		Claims claims;

		// 토큰이 있는 경우에만 게시물 업데이트 가능
		if (token != null) {
			// Token 검증
			if (jwtUtil.validateToken(token)) {
				// 토큰에서 사용자 정보 가져오기
				claims = jwtUtil.getUserInfoFromToken(token);
			} else {
				throw new IllegalArgumentException("Token Error");
			}

			// 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
			User user = userRepository.findByEmail(claims.getSubject()).orElseThrow(
				() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
			);

			// 게시물이 있는지 없는지, 게시물의 id도 가지고 와서 게시물이 있는지 없는지 확인 했음
			Notice notice = noticeRepository.findById(id).orElseThrow(
				() -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
			);

			if (notice.getUser().getEmail().equals(user.getEmail())) {

				notice.update(requestDto);

				return new NoticeResponseDto(notice); // 수정된 게시글 반환

			} else return null;

		} else return null;
	}

	//게시글 삭제
	// @Transactional
	// public NoticeMessageDto deleteNotice(Long id, NoticeRequestDto requestDto) {
	// 	Notice notice = noticeRepository.findById(id).orElseThrow(
	// 		() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
	// 	);
	//
	// 	noticeRepository.deleteById(id);
	// 	return new NoticeMessageDto("게시물이 정상적으로 삭제되었습니다.");
	// }

	@Transactional
	public NoticeMessageDto deleteNotice(Long id, NoticeRequestDto requestDto, HttpServletRequest request) {
		// Request에서 Token 가져오기
		String token = jwtUtil.resolveToken(request);
		Claims claims;

		// 토큰이 있는 경우에만 관심상품 최저가 업데이트 가능
		if (token != null) {
			// Token 검증
			if (jwtUtil.validateToken(token)) {
				// 토큰에서 사용자 정보 가져오기
				claims = jwtUtil.getUserInfoFromToken(token);
			} else {
				throw new IllegalArgumentException("Token Error");
			}

			// 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
			User user = userRepository.findByEmail(claims.getSubject()).orElseThrow(
				() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
			);

			Notice notice = noticeRepository.findById(id).orElseThrow(
				() -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
			);

			if (notice.getUser().getEmail().equals(user.getEmail())) {

				noticeRepository.deleteById(id);

				return new NoticeMessageDto("삭제 성공");
			} else {
				return new NoticeMessageDto("삭제 실패");
			}

		}
		return new NoticeMessageDto("삭제 실패");
	}
}
