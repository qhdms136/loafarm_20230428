package com.loafarm.subuser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.guildpost.bo.GuildServiceBO;
import com.loafarm.subuser.bo.SubUserBO;
import com.loafarm.subuser.model.SubUser;

@RequestMapping("/subuser")
@RestController
public class SubUserRestController {
	
	@Autowired
	private SubUserBO subUserBO;
	
	@Autowired
	private GuildServiceBO guildServiceBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		// insert db
		/*
		 * subUserBO.addSubUserByUserIdPostId(userId, postId, content);
		 * result.put("code", 1); result.put("result", "성공");
		 */
		int rowCount = guildServiceBO.getSubUserFilter(userId, postId, content);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else if(rowCount == 0) {
			result.put("code", 300);
			result.put("errorMessage", "신청자가 포화상태입니다. 나중에 다시 신청해주세요");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "신청 중 서버 오류가 발생했습니다.");
		}
		return result;
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("userId") int userId,
			@RequestParam("postId") int postId,
			@RequestParam("state") String state){
		subUserBO.updateSubUser(userId, postId, state);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedUserId(
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		// select
		SubUser subUser = subUserBO.getSubUserByUserId(userId);
		if(subUser != null) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 500);
			result.put("result", false);
		}
		return result;
	}
	
}
