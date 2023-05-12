package com.loafarm.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.user.model.User;

@Repository
public interface UserMapper {
	public User selectUserByLoginId(String loginId);
	
	public User selectUserByNickname(String nickname);
	
	public int insertUser(
			@Param("loginId") String loginId,
			@Param("nickname") String nickname,
			@Param("password") String password,
			@Param("email") String email);
	
	public void updateUserByPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public User selectUserById(int id);
}
