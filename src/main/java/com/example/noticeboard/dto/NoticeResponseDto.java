package com.example.noticeboard.dto;

import java.time.LocalDateTime;

import com.example.noticeboard.entity.Notice;

import lombok.Getter;

@Getter
public class NoticeResponseDto {
	private Long id;

	private String email;

	private String title;

	private String contents;

	private LocalDateTime createDate;

	private LocalDateTime modifiedDate;

	public NoticeResponseDto(Notice notice) {
		this.id = notice.getId();
		this.email = notice.getUser().getEmail();
		this.title = notice.getTitle();
		this.contents = notice.getContents();
		this.createDate = notice.getCreatedAt();
		this.modifiedDate = notice.getModifiedAt();
	}
}
