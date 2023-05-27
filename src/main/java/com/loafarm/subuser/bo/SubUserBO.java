package com.loafarm.subuser.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.subuser.dao.SubUserMapper;
import com.loafarm.subuser.model.SubUser;

@Service
public class SubUserBO {
	
	@Autowired
	private SubUserMapper subUserMapper;
	
	public void addSubUserByUserIdPostId(int userId, int postId, String content) {
		subUserMapper.insertSubUserByUserIdPostId(userId, postId, content);
	}
	
	public SubUser getSubUserByUserId(int userId) {
		return subUserMapper.selectSubUserByUserId(userId);
	}
}
