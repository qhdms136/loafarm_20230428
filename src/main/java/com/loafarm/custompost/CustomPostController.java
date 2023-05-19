package com.loafarm.custompost;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/custom")
@Controller
public class CustomPostController {
	
	@GetMapping("/custom_list_view")
	public String customListView(Model model) {
		model.addAttribute("view", "custom/customPost");
		return "template/layout";
	}
	
	
	@GetMapping("/custom_create_view")
	public String customCreateView(Model model) {
		model.addAttribute("view", "custom/customCreate");
		return "template/layout";
	}
}
