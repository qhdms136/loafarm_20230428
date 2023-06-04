package com.loafarm.noticepost;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.noticepost.bo.NoticePostBO;

@RequestMapping("/notice")
@RestController
public class NoticePostRestController {
	
	@Autowired
	private NoticePostBO noticePostBO;
	
	@PostMapping("create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		//insert
		noticePostBO.addNoticePost(userId, subject, content);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
