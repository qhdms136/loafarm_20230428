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
			@Param("imagePath") String imagePath);
	
	public List<FreePost> selectFreePostList();
}