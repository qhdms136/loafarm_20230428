package com.loafarm.subuser.model;

import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.user.model.User;

public class SubGuildPost {
	private SubUser subuser;
	private GuildPost guildpost;
	private User user;
	
	public SubUser getSubuser() {
		return subuser;
	}
	public void setSubuser(SubUser subuser) {
		this.subuser = subuser;
	}
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
