package com.loafarm.custompost.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.comment.bo.CommentBO;
import com.loafarm.comment.model.CommentView;
import com.loafarm.common.FileManagerService;
import com.loafarm.custompost.dao.CustomPostMapper;
import com.loafarm.custompost.model.CustomPost;
import com.loafarm.custompost.model.CustomPostView;
import com.loafarm.recommend.bo.RecommendBO;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@Service
public class CustomPostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomPostMapper customPostMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private RecommendBO recommendBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public int addCustomPost(
			int userId,
			String userLoginId,
			String subject,
			String type,
			MultipartFile file) {
		// 예외처리
		String imagePath = null;
		// 서버에 이미지 업로드 후 imagePath에 받아옴
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		return  customPostMapper.insertCustomPost(userId, subject, type, imagePath);
	}
	
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<CustomPostView> generateCustomPostViewList(Integer userId){
		List<CustomPostView> customPostViewList = new ArrayList<>();
		List<CustomPost> customPostList = new ArrayList<>();
		// 글 목록 가져오기
		customPostList = customPostMapper.selectCustomPostList();
		
		// customPostList 반복 >> CustomPost -> CustomPostView => customPostViewList에 넣기
		for(CustomPost custompost : customPostList) {
			CustomPostView customPostView = new CustomPostView();
			
			// 글
			customPostView.setCustompost(custompost);
			
			// 글쓴이 정보
			User user = userBO.getUserById(custompost.getUserId());
			customPostView.setUser(user);
			
			// 카드 리스트 채우기
			customPostViewList.add(customPostView);
		}
		return customPostViewList;
	}
	
	// 커스터마이징 상세페이지
	public CustomPostView generateCustomPostView(int customPostId, int userId, String type) {
		// 커스터마이징 상세 게시물 1개
		CustomPostView customPostView = new CustomPostView();
		
		// 글
		CustomPost customPost = customPostMapper.selectCustomPostById(customPostId);
		customPostView.setCustompost(customPost);
		
		// 글쓴이 정보
		User user = userBO.getUserById(customPost.getUserId());
		customPostView.setUser(user);
		// 추천 여부
		customPostView.setFilledRecommend(recommendBO.existRecommend(userId, customPostId, type));
		
		// 추천 개수
		int recommendCount = recommendBO.selectRecommendCountByPostIdType(customPostId, type);
		customPostMapper.updateRecommendCount(customPostId, type, recommendCount);
		customPostView.setRecommendCount(recommendCount);
		// 댓글 들
		List<CommentView> commentList = commentBO.generateCommentList(customPostId, type);
		customPostView.setCommentList(commentList);
		
		return customPostView;
	}
}
