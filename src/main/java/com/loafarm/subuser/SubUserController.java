package com.loafarm.subuser;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loafarm.guildpost.bo.GuildServiceBO;
import com.loafarm.guildpost.model.GuildPost;
import com.loafarm.subuser.bo.SubUserBO;
import com.loafarm.subuser.model.SubUserView;

@RequestMapping("/subuser")
@Controller
public class SubUserController {
	
	@Autowired
	private SubUserBO subUserBO;
	
	@Autowired
	private GuildServiceBO guildServiceBO;
	
	@GetMapping("/subuser_list")
	public String subUserList(Model model,
			@RequestParam(value="postId", required=false, defaultValue="0") int postId,
			HttpSession session) {
		List<SubUserView> subUserViewList = subUserBO.getSubUserByPostId(postId);
		GuildPost guildpost = guildServiceBO.getGuildPostByPostId(postId);
		int subcount = subUserBO.getSubUserCountByPostId(postId);
		model.addAttribute("subcount", subcount);
		model.addAttribute("subUserList", subUserViewList);
		model.addAttribute("guildpost", guildpost);
		model.addAttribute("view", "subuser/subUserList");
		return "template/layout";
	}
}
