package com.loafarm.freepost.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.common.FileManagerService;
import com.loafarm.freepost.dao.FreePostMapper;

@Service
public class FreePostBO {
	
	@Autowired
	private FreePostMapper freePostMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
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
}
