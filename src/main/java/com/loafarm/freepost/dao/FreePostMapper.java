package com.loafarm.freepost.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.freepost.model.FreePost;

@Repository
public interface FreePostMapper {
	public int insertFreePost(
			@Param("userId") int userId, 
			@Param("category") String category, 
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("type") String type,
			@Param("imagePath") String imagePath);
	
	public List<FreePost> selectFreePostList();
	
	public FreePost selectFreePostById(int freePostId);
	
	public void updateFreePostByPostId(
			@Param("freePostId") int freePostId,
			@Param("category") String category,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public FreePost selectFreePostByPostIdUserId(
			@Param("freePostId") int freePostId,
			@Param("userId") int userId);
	
	public List<FreePost> selectFreePostListByCategory(String category);
	
	public void updateRecommendCount(
			@Param("freePostId") int freePostId,
			@Param("type") String type,
			@Param("recommendCount") int recommendCount);
}
