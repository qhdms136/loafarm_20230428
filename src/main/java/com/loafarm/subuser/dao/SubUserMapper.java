package com.loafarm.subuser.dao;

import java.util.List;

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
	
	public void deleteSubUserByPostId(int postId);
	
	public List<SubUser> selectSubUserByPostId(int postId);
	
	public int selectSubUserCountByPostId(int postId);
}
