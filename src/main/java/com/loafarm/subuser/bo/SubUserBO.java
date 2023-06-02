package com.loafarm.subuser.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.subuser.dao.SubUserMapper;
import com.loafarm.subuser.model.SubUser;
import com.loafarm.subuser.model.SubUserView;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@Service
public class SubUserBO {
	
	@Autowired
	private SubUserMapper subUserMapper;
	
	@Autowired
	private UserBO userBO;
	
	public int addSubUserByUserIdPostId(int userId, int postId, String content) {
		return subUserMapper.insertSubUserByUserIdPostId(userId, postId, content);
	}
	
	// 수락 / 거절 update
	public void updateSubUser(int userId, int postId, String state){
		subUserMapper.updateSubUser(userId, postId, state);
	}
	
	public List<SubUser> getSubUserListByUserId(int userId){
		return subUserMapper.selectSubUserListByUserId(userId);
	}
	
	public SubUser getSubUserByUserId(int userId) {
		return subUserMapper.selectSubUserByUserId(userId);
	}
	
	public List<SubUserView> getSubUserByPostId(int postId){
		List<SubUserView> subUserViewList = new ArrayList<>();
		List<SubUser> subUserList = new ArrayList<>();
		subUserList = subUserMapper.selectSubUserByPostId(postId);
		for(SubUser subuser : subUserList) {
			SubUserView subUserView = new SubUserView();
			
			// 신청 유저의 글 정보
			subUserView.setSubuser(subuser);
			
			// 신청 유저의 정보
			User user = userBO.getUserById(subuser.getUserId());
			subUserView.setUser(user);
			
			int subcount = subUserMapper.selectSubUserCountByPostId(postId);
			subUserView.setSubcount(subcount);
			
			subUserViewList.add(subUserView);
		}
		return subUserViewList;
	}
	
	public void deleteSubUserByPostId(int postId) {
		subUserMapper.deleteSubUserByPostId(postId);
	}
	
	public int deleteSubUserByPostIdUserId(int postId, int userId) {
		return subUserMapper.deleteSubUserByPostIdUserId(postId, userId);
	}
	
	public int getSubUserCountByPostId(int postId) {
		return subUserMapper.selectSubUserCountByPostId(postId);
	}
}
