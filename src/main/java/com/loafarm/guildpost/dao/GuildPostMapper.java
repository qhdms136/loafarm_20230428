package com.loafarm.guildpost.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildPostMapper {
	
	public void insertGuildPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("address") String address,
			@Param("maxCount") int maxCount,
			@Param("content") String content);
}
