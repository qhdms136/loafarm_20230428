package com.loafarm.comment.model;

import com.loafarm.user.model.User;

public class CommentView {
	// 댓글 하나
	private Comment comment;
	// 댓글 쓴 유저
	private User user;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
