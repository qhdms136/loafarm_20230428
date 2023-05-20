package com.loafarm.custompost.model;

import java.util.List;

import com.loafarm.comment.model.CommentView;
import com.loafarm.user.model.User;

public class CustomPostView {
	// 커스터마이징 글 1개
	private CustomPost custompost;
	
	// 글쓴이 정보
	private User user;
	
	// 추천 여부
	private boolean filledRecommend;
	
	// 추천 갯수
	private int recommendCount;
	
	// 댓글 들
	private List<CommentView> commentList;

	public CustomPost getCustompost() {
		return custompost;
	}

	public void setCustompost(CustomPost custompost) {
		this.custompost = custompost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFilledRecommend() {
		return filledRecommend;
	}

	public void setFilledRecommend(boolean filledRecommend) {
		this.filledRecommend = filledRecommend;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}
	
	
}
