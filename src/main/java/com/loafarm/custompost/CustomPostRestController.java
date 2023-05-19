package com.loafarm.custompost;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.custompost.bo.CustomPostBO;

@RequestMapping("/custom")
@RestController
public class CustomPostRestController {
	
	@Autowired
	private CustomPostBO customPostBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("type") String type,
			@RequestParam("file") MultipartFile file,
			HttpSession session){
		// 유저 정보 가져오기
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		Map<String, Object> result = new HashMap<>();
		// insert
		int rowCount = customPostBO.addCustomPost(userId, userLoginId, subject, type, file);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "글을 저장하는데 실패하였습니다.");
		}
		return result;
	}
}
