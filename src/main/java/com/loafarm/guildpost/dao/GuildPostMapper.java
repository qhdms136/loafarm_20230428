package com.loafarm.guildpost.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.guildpost.model.GuildPost;

@Repository
public interface GuildPostMapper {
	
	public void insertGuildPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("address") String address,
			@Param("maxCount") int maxCount,
			@Param("content") String content);
	
	public List<GuildPost> selectGuildPostList();
	
	public List<GuildPost> selectGuildPostMyList(int userId);
	
	public GuildPost selectGuildPostById(int guildPostId);
	
	public GuildPost selectGuildPostByUserId(int userId);
	
	public void updateGuildPost(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("address") String address,
			@Param("maxCount") int maxCount,
			@Param("content") String content);
}
