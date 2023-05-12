package com.loafarm.freepost.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.common.FileManagerService;
import com.loafarm.freepost.dao.FreePostMapper;
import com.loafarm.freepost.model.FreePost;
import com.loafarm.freepost.model.FreePostView;
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
	
	public int addFreePost(int userId, 
			String loginId, 
			String category, 
			String subject, 
			String content, 
			MultipartFile file) {
		// 예외처리
		String imagePath = null; // 임시 null
		// 서버에 이미지 업로드 후 imagePath에 받아옴
		if(file != null) {
			imagePath = fileManager.saveFile(loginId, file);
		}
		return freePostMapper.insertFreePost(userId, category, subject, content, imagePath);
	}
	
	// 비 로그인시에도 게시판 목록을 볼 수 있게 null 허용
	public List<FreePostView> generateFreePostViewList(Integer userId){
		List<FreePostView> freePostViewList = new ArrayList<>();
		
		// 글 목록 가져오기
		List<FreePost> freePostList = freePostMapper.selectFreePostList();
		
		// freePostList 반복 >> 1:1 freePost ->FreePostView => freePostViewList에 넣는다.
		// 향상된 for문
		// for(변수타입 : 리스트)
		for(FreePost freepost : freePostList) {
			FreePostView freePostView = new FreePostView();
			
			// 글
			freePostView.setFreepost(freepost); // 지금 가져온 글
			
			// 글쓴이 정보
			User user = userBO.getUserById(freepost.getId());
			freePostView.setUser(user);
		}
		return freePostViewList;
	}
}