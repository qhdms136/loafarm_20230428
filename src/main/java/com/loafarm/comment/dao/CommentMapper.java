package com.loafarm.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
	
	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("type") String type);
}
