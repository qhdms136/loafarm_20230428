package com.loafarm.noticepost.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.noticepost.model.NoticePost;

@Repository
public interface NoticePostMapper {
	//insert
	public void insertNoticePost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content);
	
	public List<NoticePost> selectNoticePostListLimit(
			@Param("pagingStart") int pagingStart,
			@Param("pageLimit") int pageLimit);
	
	public int selectNoticePostListCount();
}
