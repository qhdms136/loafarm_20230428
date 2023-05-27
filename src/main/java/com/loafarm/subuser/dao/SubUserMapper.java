package com.loafarm.subuser.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.subuser.model.SubUser;

@Repository
public interface SubUserMapper {
	public void insertSubUserByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("content") String content);
	
	public SubUser selectSubUserByUserId(int userId);
}
