package com.loafarm.custompost.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.common.FileManagerService;
import com.loafarm.custompost.dao.CustomPostMapper;

@Service
public class CustomPostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomPostMapper customPostMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
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
}
