package com.loafarm.guildpost.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.guildpost.model.GuildPost;

@Repository
public interface GuildPostMapper {
	
	// 추가
	public void insertGuildPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("address") String address,
			@Param("maxCount") int maxCount,
			@Param("content") String content);
	
	public List<GuildPost> selectGuildPostList();
	
	public List<GuildPost> selectGuildPostListLimit(
			@Param("pagingStart") int pagingStart,
			@Param("pageLimit") int pageLimit);
	
	public int selectGuildPostListCount();
	
	public List<GuildPost> selectGuildPostMyList(int userId);
	
	public GuildPost selectGuildPostById(int guildPostId);
	
	public GuildPost selectGuildPostByUserId(int userId);
	
	// 업뎃
	public void updateGuildPost(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("address") String address,
			@Param("maxCount") int maxCount,
			@Param("content") String content);
	
	// 삭제
	public int deleteGuildPostByPostIdUserId(
			@Param("guildPostId") int guildPostId,
			@Param("userId") int userId);
}
