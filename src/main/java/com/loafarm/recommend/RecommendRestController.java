package com.loafarm.recommend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.recommend.bo.RecommendBO;

@RestController
public class RecommendRestController {
	
	@Autowired
	private RecommendBO recommendBO;
	
	@RequestMapping("/recommend/{postId}/{type}")
	public Map<String, Object> recommend(
			@PathVariable int postId,
			@PathVariable String type,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		int userId = (int)session.getAttribute("userId");
		
		// recommend 체크 여부
		recommendBO.recommendToggle(userId, postId, type);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
