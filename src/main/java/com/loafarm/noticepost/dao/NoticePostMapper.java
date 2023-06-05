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
	
	public void deleteNoticePostByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public void updateNoticePostByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("subject") String subject,
			@Param("content") String content);
	
	public NoticePost selectNoticePostByPostId(int postId);
	
	public int selectNoticePostListCount();
}
