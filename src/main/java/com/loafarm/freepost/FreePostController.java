package com.loafarm.freepost;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/free")
@Controller
public class FreePostController {
	@GetMapping("/free_post_view")
	public String freePostView(Model model) {
		model.addAttribute("view", "free/freePost");
		return "template/layout";
	}
}
