package com.loafarm.freepost.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FreePostMapper {
	public int insertFreePost(
			@Param("userId") int userId, 
			@Param("category") String category, 
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
}
