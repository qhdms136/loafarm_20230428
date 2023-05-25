package com.loafarm.guildpost.model;

import com.loafarm.user.model.User;

public class GuildPostView {
	// 길드 게시판 글 1개
	private GuildPost guildpost;
	
	// 글쓴이 정보
	private User user;

	// 추천 갯수
	
	
	
	public GuildPost getGuildpost() {
		return guildpost;
	}

	public void setGuildpost(GuildPost guildpost) {
		this.guildpost = guildpost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
