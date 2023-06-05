package com.loafarm.noticepost;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("postId") int postId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		// update db
		noticePostBO.updateNoticePost(userId, postId, subject, content);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		// delete
		noticePostBO.deleteNoticePostByUserIdPostId(userId, postId);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
}
