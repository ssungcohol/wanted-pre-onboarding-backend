package com.example.noticeboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.noticeboard.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

	List<Notice> findAllByOrderByModifiedAtDesc();
}
