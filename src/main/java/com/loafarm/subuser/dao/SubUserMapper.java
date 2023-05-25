package com.loafarm.subuser.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubUserMapper {
	public void insertSubUserByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("content") String content);
}
