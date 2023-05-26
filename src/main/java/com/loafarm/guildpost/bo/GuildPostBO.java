package com.loafarm.guildpost.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.guildpost.dao.GuildPostMapper;
import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.guildpost.model.GuildPostView;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@Service
public class GuildPostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GuildPostMapper guildPostMapper;
	
	@Autowired
	private UserBO userBO;
	
	public void addGuildPost(int userId, String subject, String address, int maxCount, String content) {
		logger.info("[subject:{}, address:{}, maxCount:{}, content:{}]", subject, address, maxCount, content);
		guildPostMapper.insertGuildPost(userId, subject, address, maxCount, content);
	}
	
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<GuildPostView> generateGuildPostViewList(Integer userId){
		List<GuildPostView> guildPostViewList = new ArrayList<>();
		List<GuildPost> guildPostList = new ArrayList<>();
		// 글 목록 가져오기
		guildPostList = guildPostMapper.selectGuildPostList();
		
		// 반복문
		for(GuildPost guildpost : guildPostList) {
			GuildPostView guildPostView = new GuildPostView();
			
			// 글
			guildPostView.setGuildpost(guildpost);
			
			// 글쓴이 정보
			User user = userBO.getUserById(guildpost.getUserId());
			guildPostView.setUser(user);
			
			// 카드 리스트 채우기
			guildPostViewList.add(guildPostView);
		}
		return guildPostViewList;
	}
	
	// 내 글목록 가져오기
	public List<GuildPostView> generateGuildPostViewMyList(int userId){
		List<GuildPostView> guildPostViewList = new ArrayList<>();
		List<GuildPost> guildPostList = new ArrayList<>();
		// 글 목록 가져오기
		guildPostList = guildPostMapper.selectGuildPostMyList(userId);
		
		// 반복문
		for(GuildPost guildpost : guildPostList) {
			GuildPostView guildPostView = new GuildPostView();
			
			// 글
			guildPostView.setGuildpost(guildpost);
			
			// 글쓴이 정보
			User user = userBO.getUserById(guildpost.getUserId());
			guildPostView.setUser(user);
			
			// 카드 리스트 채우기
			guildPostViewList.add(guildPostView);
		}
		return guildPostViewList;
	}
	
	// 길드모임 상세 페이지
	public GuildPostView generateGuildPostView(int guildPostId, int userId) {
		// 길드모임 상세 게시물 1개
		GuildPostView  guildPostView = new GuildPostView();
		
		// 글
		GuildPost guildPost = guildPostMapper.selectGuildPostById(guildPostId);
		guildPostView.setGuildpost(guildPost);
		
		// 글쓴 유저 정보
		User user = userBO.getUserById(guildPost.getUserId());
		guildPostView.setUser(user);
		
		return guildPostView;
	}
	
	public GuildPost getGuildPostByPostIdUserId(int guildPostId, int userId) {
		return guildPostMapper.selectGuildPostById(guildPostId);
	}
}
