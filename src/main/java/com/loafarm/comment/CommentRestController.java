package com.loafarm.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			@RequestParam("type") String type,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		int userId = (int)session.getAttribute("userId");
		
		// 댓글 추가
		int rowCount = commentBO.addComment(postId, userId, content, type);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("reuslt", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글 작성에 실패했습니다.");
		}
		return result;
	}
	
	@RequestMapping("/delete/{commentId}")
	public Map<String, Object> commentDelete(
			@PathVariable int commentId){
		Map<String, Object> result = new HashMap<>();
		
		// 댓글 delete
		commentBO.deleteComment(commentId);
		
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
