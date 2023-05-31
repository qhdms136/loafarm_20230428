package com.loafarm.subuser.model;

import com.loafarm.user.model.User;

public class SubUserView {
	
	private SubUser subuser;
	private User user;
	private int subcount;
	
	public SubUser getSubuser() {
		return subuser;
	}
	public void setSubuser(SubUser subuser) {
		this.subuser = subuser;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getSubcount() {
		return subcount;
	}
	public void setSubcount(int subcount) {
		this.subcount = subcount;
	}
	
	
	
}
