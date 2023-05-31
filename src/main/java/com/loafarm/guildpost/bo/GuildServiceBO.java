package com.loafarm.guildpost.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.subuser.bo.SubUserBO;

@Service
public class GuildServiceBO {
	@Autowired
	private GuildPostBO guildPostBO;
	
	@Autowired
	private SubUserBO subUserBO;
	
	public GuildPost getGuildPostByPostId(int postId) {
		return guildPostBO.getGuildPostByPostId(postId);
	}
}
