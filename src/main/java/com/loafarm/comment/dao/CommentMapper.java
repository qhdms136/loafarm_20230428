package com.loafarm.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.comment.model.Comment;

@Repository
public interface CommentMapper {
	
	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("type") String type);
	
	public List<Comment> selctCommentListByPostIdType(
			@Param("postId") int postId,
			@Param("type") String type);
	
	public void deleteComment(int id);
	
	public void deleteCommentByPostIdType(
			@Param("postId") int postId,
			@Param("type") String type);
}
