package com.loafarm.freepost.model;

import com.loafarm.user.model.User;

public class FreePostView {
	// 자유 게시판 글 1개
	private FreePost freepost;
	
	// 글쓴이 정보
	private User user;
	
	// 추천 여부
	private boolean filledRecommend;
	
	// 추천 갯수
	

	public boolean isFilledRecommend() {
		return filledRecommend;
	}

	public void setFilledRecommend(boolean filledRecommend) {
		this.filledRecommend = filledRecommend;
	}

	public FreePost getFreepost() {
		return freepost;
	}

	public void setFreepost(FreePost freepost) {
		this.freepost = freepost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
