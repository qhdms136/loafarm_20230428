package com.loafarm.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.comment.dao.CommentMapper;

@Service
public class CommentBO {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public int addComment(int postId, int userId, String content, String type) {
		return commentMapper.insertComment(postId, userId, content, type);
	}
}
