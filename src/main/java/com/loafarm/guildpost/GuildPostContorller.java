package com.loafarm.guildpost;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loafarm.guildpost.bo.GuildPostBO;
import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.guildpost.model.GuildPostView;

@RequestMapping("/guild")
@Controller
public class GuildPostContorller {
	
	@Autowired
	private GuildPostBO guildPostBO;
	
	@GetMapping("/guild_list_view")
	public String guildListView(Model model,
			HttpSession session) {
		// 비 로그인 시에도 게시물 목록을 보기위해 null
		Integer userId = (Integer)session.getAttribute("userId");
		List<GuildPostView> guildPostViewList = guildPostBO.generateGuildPostViewList(userId);
		model.addAttribute("guildPostList", guildPostViewList);
		model.addAttribute("view", "guild/guildPost");
		return "template/layout";
	}
	
	@GetMapping("/guild_detail_view")
	public String guildDetailView(Model model,
			@RequestParam("guildPostId") int guildPostId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		GuildPostView guildPostView = guildPostBO.generateGuildPostView(guildPostId, userId);
		model.addAttribute("guildPostView", guildPostView);
		model.addAttribute("view", "guild/guildDetail");
		return "template/layout";
	}
	
	@GetMapping("/guild_create_view")
	public String guildCreateView(Model model) {
		model.addAttribute("view", "guild/guildCreate");
		return "template/layout";
	}
	
	@GetMapping("/guild_update_view")
	public String guildUpdateView(Model model,
			@RequestParam("guildPostId") int guildPostId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		GuildPost guildPost = guildPostBO.getGuildPostByPostIdUserId(guildPostId, userId);
		model.addAttribute("view", "guild/guildUpdate");
		return "template/layout";
	}
}
