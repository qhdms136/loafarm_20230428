package com.loafarm.guildpost;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/guild")
@Controller
public class GuildPostContorller {
	@GetMapping("/guild_list_view")
	public String guildListView(Model model) {
		model.addAttribute("view", "guild/guildPost");
		return "template/layout";
	}
	
	@GetMapping("guild_create_view")
	public String guildCreateView(Model model) {
		model.addAttribute("view", "guild/guildCreate");
		return "template/layout";
	}
}
