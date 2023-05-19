package com.loafarm.custompost.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPostMapper {
	
	public int insertCustomPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("type") String type,
			@Param("imagePath") String imagePath);
}
