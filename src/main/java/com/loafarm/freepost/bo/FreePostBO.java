package com.loafarm.freepost.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.comment.bo.CommentBO;
import com.loafarm.comment.model.CommentView;
import com.loafarm.common.FileManagerService;
import com.loafarm.freepost.dao.FreePostMapper;
import com.loafarm.freepost.model.FreePost;
import com.loafarm.freepost.model.FreePostView;
import com.loafarm.recommend.bo.RecommendBO;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@Service
public class FreePostBO {
	
	@Autowired
	private FreePostMapper freePostMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private RecommendBO recommendBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public int addFreePost(int userId, 
			String loginId, 
			String category, 
			String subject, 
			String content,
			String type,
			MultipartFile file) {
		// 예외처리
		String imagePath = null; // 임시 null
		// 서버에 이미지 업로드 후 imagePath에 받아옴
		if(file != null) {
			imagePath = fileManager.saveFile(loginId, file);
		}
		return freePostMapper.insertFreePost(userId, category, subject, content, type, imagePath);
	}
	
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<FreePostView> generateFreePostViewList(Integer userId, String category){
		List<FreePostView> freePostViewList = new ArrayList<>();
		List<FreePost> freePostList = new ArrayList<>();
		// 글 목록 가져오기
		if(category == null) {
			freePostList = freePostMapper.selectFreePostList();			
		} else {
			freePostList = freePostMapper.selectFreePostListByCategory(category);
		}
		// freePostList 반복 >> 1:1 freePost ->FreePostView => freePostViewList에 넣는다.
		// 향상된 for문
		// for(변수타입 : 리스트)
		
		for(FreePost freepost : freePostList) {
			FreePostView freePostView = new FreePostView();
			
			// 글
			freePostView.setFreepost(freepost); // 지금 가져온 글
			
			// 글쓴이 정보
			User user = userBO.getUserById(freepost.getUserId());
			freePostView.setUser(user);
			
			// 카드 리스트 꼭 채우기!!!!
			freePostViewList.add(freePostView);
		}
		return freePostViewList;
	}
	
	public FreePostView generateFreePostView(int freePostId, int userId, String type) {
		// 자유 게시판 상세게시물 1개
		FreePostView freePostView = new FreePostView();
		
		// 글 
		FreePost freePost = freePostMapper.selectFreePostById(freePostId);
		freePostView.setFreepost(freePost);
		
		// 글쓴이 정보
		User user = userBO.getUserById(freePost.getUserId());
		freePostView.setUser(user);
		
		// 추천 체크 여부
		freePostView.setFilledRecommend(recommendBO.existRecommend(userId, freePostId, type));
		
		// 추천 개수
		int recommendCount = recommendBO.selectRecommendCountByPostIdType(freePostId, type);
		freePostView.setRecommendCount(recommendCount);
		
		// 댓글 들
		List<CommentView> commentList = commentBO.generateCommentList(freePostId, type);
		freePostView.setCommentList(commentList);
		
		return freePostView;
	}
}
