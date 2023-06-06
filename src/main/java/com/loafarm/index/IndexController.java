package com.loafarm.index;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loafarm.custompost.bo.CustomPostBO;
import com.loafarm.custompost.model.CustomPostView;
import com.loafarm.freepost.bo.FreePostBO;
import com.loafarm.freepost.model.FreePostView;
import com.loafarm.guildpost.bo.GuildPostBO;
import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.noticepost.bo.NoticePostBO;
import com.loafarm.noticepost.model.NoticePost;

@RequestMapping("/index")
@Controller
public class IndexController {
	
	@Autowired
	private FreePostBO freePostBO;
	
	@Autowired
	private CustomPostBO customPostBO;
	
	@Autowired
	private NoticePostBO noticePostBO;
	
	@Autowired
	private GuildPostBO guildPostBO;
	
	@GetMapping("/index_view")
	public String indexView(Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		List<CustomPostView> customPostViewList = customPostBO.generateCustomPostViewListTop3(userId);
		List<FreePostView> freePostViewList = freePostBO.generateFreePostTodayBestViewList(userId);
		List<NoticePost> NoticePostList = noticePostBO.getNoticePost(userId);
		List<GuildPost> GuildPostList = guildPostBO.getGuildPost(userId);
		model.addAttribute("GuildPostList", GuildPostList);
		model.addAttribute("NoticePostList", NoticePostList);
		model.addAttribute("customPostList", customPostViewList);
		model.addAttribute("freePostList", freePostViewList);
		model.addAttribute("view", "index/index");
		return "template/layout";
	}
}
