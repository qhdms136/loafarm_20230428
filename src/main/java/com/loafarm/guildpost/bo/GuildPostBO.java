package com.loafarm.guildpost.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.freepost.model.Page;
import com.loafarm.guildpost.dao.GuildPostMapper;
import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.guildpost.model.GuildPostView;
import com.loafarm.subuser.bo.SubUserBO;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@Service
public class GuildPostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GuildPostMapper guildPostMapper;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private SubUserBO subUserBO;
	
	// 클래스 변수 (상수 불변)
	private static final int PAGE_LIMIT = 10; // 한 페이지당 보여줄 글 갯수
	private static final int BLOCK_LIMIT = 10; // 하단에 보여줄 페이지 번호 갯수
	
	public void addGuildPost(int userId, String subject, String address, int maxCount, String content) {
		logger.info("[subject:{}, address:{}, maxCount:{}, content:{}]", subject, address, maxCount, content);
		guildPostMapper.insertGuildPost(userId, subject, address, maxCount, content);
	}
	
	// 중복확인
	public GuildPost getGuildPostByUserId(int userId) {
		return guildPostMapper.selectGuildPostByUserId(userId);
	}
	
	public GuildPost getGuildPostByPostId(int postId) {
		return guildPostMapper.selectGuildPostById(postId);
	}
	
	// update
	public void updateGuildPost(int postId, int userId, String subject, String address, int maxCount, String content) {
		guildPostMapper.updateGuildPost(postId, userId, subject, address, maxCount, content);
	}
	
		// 길드모임 게시판 페이징 변수 설정
		public Page pagingParam(int page) {
			  // 카테고리 별 전체 글 갯수 조회
			
	        int boardCount = guildPostMapper.selectGuildPostListCount();        		
	        // 전체 페이지 갯수 계산(ex ) 10/3=3.33333 => 4)
	        int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
	        // 시작 페이지 값 계산(1, 11, 21, 31, ~~~~)
	        int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
	        // 끝 페이지 값 계산(10, 20, 30, 40, ~~~~)
	        int endPage = startPage + BLOCK_LIMIT - 1;
	        if (endPage > maxPage) {
	            endPage = maxPage;
	        }
	        Page pageDTO = new Page();
	        pageDTO.setPage(page);
	        pageDTO.setMaxPage(maxPage);
	        pageDTO.setStartPage(startPage);
	        pageDTO.setEndPage(endPage);
	        return pageDTO;
		}
	
	
	
	// 게시물 삭제 delete
	public int deleteGuildPostByPostIdUserId(int guildPostId, int userId) {
		// 삭제 목록(게시물, 신청자 목록)
		// 신청자 목록 삭제
		subUserBO.deleteSubUserByPostId(guildPostId);
		
		return guildPostMapper.deleteGuildPostByPostIdUserId(guildPostId, userId);
	}
	
	
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<GuildPostView> generateGuildPostViewList(Integer userId, int page){
		// 페이징 처리
		int pagingStart = (page -1) * PAGE_LIMIT;
		
		
		List<GuildPostView> guildPostViewList = new ArrayList<>();
		List<GuildPost> guildPostList = new ArrayList<>();
		// 글 목록 가져오기
		guildPostList = guildPostMapper.selectGuildPostListLimit(pagingStart, PAGE_LIMIT);
		
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
		
		int subcount = subUserBO.getSubUserCountByPostId(guildPostId);
		guildPostView.setSubcount(subcount);
		
		return guildPostView;
	}
	
	public GuildPost getGuildPostByPostIdUserId(int guildPostId, int userId) {
		return guildPostMapper.selectGuildPostById(guildPostId);
	}
}
