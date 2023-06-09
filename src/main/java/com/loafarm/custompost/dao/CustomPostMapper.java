package com.loafarm.custompost.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.custompost.model.CustomPost;

@Repository
public interface CustomPostMapper {
	
	
	public int insertCustomPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("type") String type,
			@Param("imagePath") String imagePath);
	
	public List<CustomPost> selectCustomPostList();
	
	public List<CustomPost> selectCustomPostListByLimit(int limit);
	
	public List<CustomPost> selectCustomPostListByLimitTop3();
	
	// 내 글 목록
	public List<CustomPost> selectCustomPostListByuserId(
			@Param("userId") int userId,
			@Param("pagingStart") int pagingStart,
			@Param("pageLimit") int pageLimit);
	
	// 내 글 목록 갯수
	public int selectCustomPostListCountByUserId(int userId);
	
	
	public List<CustomPost> selectCustomPostListByIndexAndLimit(
			@Param("index") int index,
			@Param("limit") int limit);
	
	public CustomPost selectCustomPostById(int customPostId);
	
	public void updateRecommendCount(
			@Param("customPostId") int customPostId,
			@Param("type") String type,
			@Param("recommendCount") int recommendCount);
	
	public CustomPost selectCustomPostByPostIdUserId(
			@Param("customPostId") int customPostId,
			@Param("userId") int userId);
	
	public void updateCustomPostByPostId(
			@Param("customPostId") int customPostId,
			@Param("subject") String subject,
			@Param("imagePath") String imagePath);
	
	public int deletePostByPostIdUserId(
			@Param("customPostId") int customPostId,
			@Param("userId") int userId);
	
}
