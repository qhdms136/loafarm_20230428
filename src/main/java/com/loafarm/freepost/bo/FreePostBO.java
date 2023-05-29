package com.loafarm.freepost.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.comment.bo.CommentBO;
import com.loafarm.comment.model.CommentView;
import com.loafarm.common.FileManagerService;
import com.loafarm.freepost.dao.FreePostMapper;
import com.loafarm.freepost.model.FreePost;
import com.loafarm.freepost.model.FreePostView;
import com.loafarm.freepost.model.Page;
import com.loafarm.recommend.bo.RecommendBO;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;


@Service
public class FreePostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	// 클래스 변수
	int pageLimit = 3; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
	
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
	
	public void updateFreePost(int userId, 
			String loginId,
			int freePostId,
			String category, 
			String subject, 
			String content,
			MultipartFile file) {
		// 기존 글 가져오기
		FreePost freePost = getFreePostByPostIdUserId(freePostId, userId);
		
		// null 값 체크 유무
		if(freePost == null) {
			logger.warn("[update post] freePost is null. freePostId: {}, userId: {}", freePostId, userId);
			return;	// void에선 값을 안넣고 리턴
		}
		
		// 업로드한 이미지가 있으면 서버 업로드 => imagePath받아오기
		// => 업로드 성공하면 기존 이미지 제거(업로드 실패 시 기존 이미지 사용)
		String imagePath = null;	// 초기 값
		if(file != null) {
			// 업로드
			imagePath = fileManager.saveFile(loginId, file);
			
			// 성공 여부 체크 후 기존 이미지 제거
			// imagePath 가 null이 아닐때(성공) 그리고 기존 이미지가 있을때 => (이때)기존 이미지 삭제
			if(imagePath != null && freePost.getImagePath() != null) {
				// 이미지 제거
				fileManager.deleteFile(freePost.getImagePath());	// 기존 이미지 제거
			}
		}
		
		// db update
		freePostMapper.updateFreePostByPostId(freePostId, category, subject, content, imagePath);
	}
	
	// post 수정페이지 select
	public FreePost getFreePostByPostIdUserId(int freePostId, int userId) {
		return freePostMapper.selectFreePostByPostIdUserId(freePostId, userId);
	}
	
	// post image만 delete >> delete문을 쓰면 전체 행이 삭제되므로 이미지file만 지우고 업데이트
	public void deleteImageAndUpdateByPostIdUserId(int freePostId, int userId) {
		// 기존글 가져오기(이미지 확인을 위해)
		FreePost freePost = getFreePostByPostIdUserId(freePostId, userId);
		if(freePost == null) {
			logger.error("[글 삭제] post is null. freePostId:{}, userId:{}", freePostId, userId);
			return;
		}
		
		// 기존 이미지 삭제
		if(freePost.getImagePath() != null){
			fileManager.deleteFile(freePost.getImagePath());
		}
		
		// update로 null 값 처리
		freePostMapper.deleteImageAndUpdateByPostIdUserId(freePostId, userId);
	}
	
	// 게시물 삭제
	public int deletePostByPostIdUserId(int freePostId, int userId) {
		FreePost freePost = getFreePostByPostIdUserId(freePostId, userId);
		// 삭제 목록 (DB, 추천, 댓글, 이미지 삭제(각각의 BO를 불러서 삭제))
		// 이미지 삭제
		if(freePost.getImagePath() != null){
			fileManager.deleteFile(freePost.getImagePath());
		}
		
		// 추천 삭제
		recommendBO.deleteRecommendByPostIdType(freePostId, freePost.getType());
		
		// 댓글 삭제
		commentBO.deleteCommentByPostIdType(freePostId, freePost.getType());
		
		return freePostMapper.deletePostByPostIdUserId(freePostId, userId);
	}
	// 오늘의 날짜 추천 갯수에 따른 최신순서 목록
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<FreePostView> generateFreePostTodayBestViewList(Integer userId){
		List<FreePostView> freePostViewList = new ArrayList<>();
		List<FreePost> freePostList = new ArrayList<>();
		// 오늘 날짜 문자열로 추출
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowDate = sdf.format(now);
		
		// 글 목록 가져오기 >> 미리 OrderBy로 정렬하여 List에 저장할때 순서 맞춤
		freePostList = freePostMapper.selectFreePostListOrderByPostIdRecommendcount();		
		
		// freePostList 반복 >> 1:1 freePost -> freePostTodayList => freePostViewList에 넣는다.
		// 향상된 for문
		// for(변수타입 : 리스트)
		
		
		for(FreePost freepost : freePostList) {
			FreePostView freePostToday = new FreePostView();
			String today = sdf.format(freepost.getCreatedAt());
			if(nowDate.equals(today)) {
				freePostToday.setFreepost(freepost);// 지금 가져온 글 에서 필터링
				// 글쓴이 정보
				User user = userBO.getUserById(freepost.getUserId());
				freePostToday.setUser(user);
				
				// 카드 리스트 꼭 채우기!!!!
				freePostViewList.add(freePostToday);
			}
		}
		return freePostViewList;
	}
	
	// 추천 갯수의 따른 자유 게시판 목록
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<FreePostView> generateFreePostRecommendViewList(Integer userId, int recommendCount){
		List<FreePostView> freePostViewList = new ArrayList<>();
		List<FreePost> freePostList = new ArrayList<>();
		// 글 목록 가져오기
		freePostList = freePostMapper.selectFreePostListByRecommendCount(recommendCount);			
		
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
	
	// 1usage new
	public Page pagingParam(int page) {
		  // 전체 글 갯수 조회
        int boardCount = freePostMapper.selectFreePostListCount();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
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
	
	
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<FreePostView> generateFreePostViewList(Integer userId, String category, int page){
		
		int pagingStart = (page -1) * pageLimit;
		// 파라미터 @Param() 할때 오류남
		 Map<String, Integer> pagingParams = new HashMap<>();
		 pagingParams.put("start", pagingStart);
		 pagingParams.put("limit", pageLimit);
		
		
		List<FreePostView> freePostViewList = new ArrayList<>();
		List<FreePost> freePostList = new ArrayList<>();
		// 글 목록 가져오기
		if(category == null) {
			freePostList = freePostMapper.selectFreePostList(pagingStart, pageLimit);			
		} else {
			freePostList = freePostMapper.selectFreePostListByCategory(category, pagingStart, pageLimit);
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
		freePostMapper.updateRecommendCount(freePostId, type, recommendCount);
		freePostView.setRecommendCount(recommendCount);
		
		// 댓글 들
		List<CommentView> commentList = commentBO.generateCommentList(freePostId, type);
		freePostView.setCommentList(commentList);
		
		return freePostView;
	}
}
