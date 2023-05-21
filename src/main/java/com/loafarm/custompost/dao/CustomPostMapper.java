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
	
	public CustomPost selectCustomPostById(int customPostId);
	
	public void updateRecommendCount(
			@Param("customPostId") int customPostId,
			@Param("type") String type,
			@Param("recommendCount") int recommendCount);
}
