package com.loafarm.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.user.dao.UserMapper;
import com.loafarm.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId);
	}
	
	public User getUserByNickname(String nickname) {
		return userMapper.selectUserByNickname(nickname);
	}
}
