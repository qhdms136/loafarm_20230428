package com.loafarm.freepost;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/free")
@Controller
public class FreePostController {
	@GetMapping("/free_list_view")
	public String freeListView(Model model) {
		model.addAttribute("view", "free/freePost");
		return "template/layout";
	}
	
	@GetMapping("/free_detail_view")
	public String freeDetailView(Model model) {
		model.addAttribute("view", "free/freeDetail");
		return "template/layout";
	}
	
	@GetMapping("/free_create_view")
	public String freeCreateView(Model model) {
		model.addAttribute("view", "free/freeCreate");
		return "template/layout";
	}
}
