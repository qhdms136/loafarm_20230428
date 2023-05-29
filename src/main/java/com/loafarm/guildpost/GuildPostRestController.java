package com.loafarm.guildpost;

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

import com.loafarm.guildpost.bo.GuildPostBO;
import com.loafarm.guildpost.model.GuildPost;

@RequestMapping("/guild")
@RestController
public class GuildPostRestController {
	
	@Autowired
	private GuildPostBO guildPostBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("address") String address,
			@RequestParam("maxCount") int maxCount,
			@RequestParam("content") String content,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		// db
		guildPostBO.addGuildPost(userId, subject, address, maxCount, content);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("postId") int postId,
			@RequestParam("subject") String subject,
			@RequestParam("address") String address,
			@RequestParam("maxCount") int maxCount,
			@RequestParam("content") String content,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		guildPostBO.updateGuildPost(postId, userId, subject, address, maxCount, content);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("guildPostId") int guildPostId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		// delete
		int rowCount = guildPostBO.deleteGuildPostByPostIdUserId(guildPostId, userId);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "게시물 삭제에 실패하였습니다.");
		}
		return result;
	}
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedUserId(
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		// select
		GuildPost guildPost = guildPostBO.getGuildPostByUserId(userId);
		if(guildPost != null) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 500);
			result.put("result", false);
		}
		return result;
	}
}
