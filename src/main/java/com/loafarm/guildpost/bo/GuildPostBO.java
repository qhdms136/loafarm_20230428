package com.loafarm.guildpost.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.guildpost.dao.GuildPostMapper;

@Service
public class GuildPostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GuildPostMapper guildPostMapper;
	
	public void addGuildPost(int userId, String subject, String address, int maxCount, String content) {
		logger.info("[subject:{}, address:{}, maxCount:{}, content:{}]", subject, address, maxCount, content);
		guildPostMapper.insertGuildPost(userId, subject, address, maxCount, content);
	}
}
