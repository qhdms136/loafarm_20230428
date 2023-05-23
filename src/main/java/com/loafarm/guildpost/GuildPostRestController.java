package com.loafarm.guildpost;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.guildpost.bo.GuildPostBO;

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
}
