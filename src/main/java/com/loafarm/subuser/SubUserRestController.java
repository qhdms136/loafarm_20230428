package com.loafarm.subuser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.subuser.bo.SubUserBO;
import com.loafarm.subuser.model.SubUser;

@RequestMapping("/subuser")
@RestController
public class SubUserRestController {
	
	@Autowired
	private SubUserBO subUserBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		// insert db
		subUserBO.addSubUserByUserIdPostId(userId, postId, content);
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
