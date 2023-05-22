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
	
	// customPost update
	public void updateCustomPost(int userId,
			String userLoginId,
			int customPostId,
			String subject,
			MultipartFile file) {
		// 기존 글 가져오기
		CustomPost customPost = getCustomPostByPostIdUserId(customPostId, userId);
		
		// null 값 체크 유무
		if(customPost == null) {
			logger.warn("[update post] customPost is null. customPostId: {}, userId: {}", customPostId, userId);
			return; // void 값 없음
		}
		
		// 업로드한 이미지가 있으면 서버 업로드 => imagePath받아오기
		// ==> 업로드 성공하면 기존 이미지 제거(업로드 실패 시 기존 이미지로 대처)
		String imagePath = null; // 초기 값
		if(file != null) {
			// 업로드
			imagePath = fileManager.saveFile(userLoginId, file);
			
			// 성공 여부 체크 후 기존 이미지 제거
			// imagePath 가 null이 아닐때(성공) 그리고 기존 이미지가 있을때 => 이때 기존 이미지 삭제
			if(imagePath != null && customPost.getImagePath() != null) {
				// 이미지 제거
				fileManager.deleteFile(customPost.getImagePath()); // 기존 이미지 제거
			}
		}
		// db update
		customPostMapper.updateCustomPostByPostId(customPostId, subject, imagePath);
	}
	
	// 커스터마이징 게시물 삭제
	public int deletePostByPostIdUserId(int customPostId, int userId) {
		CustomPost customPost = getCustomPostByPostIdUserId(customPostId, userId);
		// 삭제 목록 (DB, 추천, 댓글, 이미지 삭제)
		// 이미지 삭제
		if(customPost.getImagePath() != null) {
			fileManager.deleteFile(customPost.getImagePath());
		}
		
		// 추천 삭제
		recommendBO.deleteRecommendByPostIdType(customPostId, customPost.getType());
		
		// 댓글 삭제
		commentBO.deleteCommentByPostIdType(customPostId, customPost.getType());
		
		return customPostMapper.deletePostByPostIdUserId(customPostId, userId);
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
	
	// custompost 수정페이지 select view용
	public CustomPost getCustomPostByPostIdUserId(int customPostId, int userId) {
		return customPostMapper.selectCustomPostByPostIdUserId(customPostId, userId);
	}
	
	// 추천 갯수에 따른 커스터마이징 목록
	// 비 로그인 시에도 볼 수 있도록 null
	public List<CustomPostView> generateCustomPostRecommendViewList(Integer userId){
		List<CustomPostView> customPostViewList = new ArrayList<>();
		List<CustomPost> customPostList = new ArrayList<>();
		
		// 글 목록 가져오기
		customPostList = customPostMapper.selectCustomPostListOrderByPostIdRecommendCount();
		
		// customPostList 반복 >> CustomPost -> CustomPostView => customPostViewList에 넣기
		for (CustomPost custompost : customPostList) {
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
}
