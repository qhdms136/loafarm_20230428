package com.loafarm.recommend.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendMapper {
	
	public int selectRecommendCountByPostIdUserIdType(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("type") String type);
	
	public void deleteByPostIdUserIdType(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("type") String type);
	
	public void insertByPostIdUserIdType(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("type") String type);
}
