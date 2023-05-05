package com.loafarm.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/index_view")
	public String indexView(Model model) {
		model.addAttribute("view", "index/index");
		return "template/layout";
	}
}
