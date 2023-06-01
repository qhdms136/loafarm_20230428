package com.loafarm.guildpost.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.subuser.bo.SubUserBO;
import com.loafarm.subuser.model.SubGuildPost;
import com.loafarm.subuser.model.SubUser;

@Service
public class GuildServiceBO {
	@Autowired
	private GuildPostBO guildPostBO;
	
	@Autowired
	private SubUserBO subUserBO;
	
	public GuildPost getGuildPostByPostId(int postId) {
		return guildPostBO.getGuildPostByPostId(postId);
	}
	
	public int getSubUserFilter(int userId, int postId, String content) {
		GuildPost guildpost = guildPostBO.getGuildPostByPostId(postId);
		int maxCount = guildpost.getMaxCount();
		int subCount = subUserBO.getSubUserCountByPostId(postId);
		if(subCount >= maxCount) {
			return 0;
		} else {
			return subUserBO.addSubUserByUserIdPostId(userId, postId, content);
		}
	}
	
 	public List<SubGuildPost> generateSubGuildPostByUserId(int userId){
 		// 신청자 목록
 		List<SubGuildPost> subGuildPostList = new ArrayList<>();
 		List<SubUser> subUserList = new ArrayList<>();
 		subUserList = subUserBO.getSubUserListByUserId(userId);
 		for(SubUser subuser : subUserList) {
 			SubGuildPost subGuildPost = new SubGuildPost();
 			subGuildPost.setSubuser(subuser);
 			// 신청 게시물 정보
 			GuildPost guildpost = guildPostBO.getGuildPostByPostId(subuser.getPostId());
 			subGuildPost.setGuildpost(guildpost);
 			
 			subGuildPostList.add(subGuildPost);
 		}
 		return subGuildPostList;
 	}
}
