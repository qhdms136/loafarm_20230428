package com.loafarm.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loafarm.freepost.bo.FreePostBO;

@RequestMapping("/index")
@Controller
public class IndexController {
	
	@Autowired
	private FreePostBO freePostBO;
	
	@GetMapping("/index_view")
	public String indexView(Model model) {
		model.addAttribute("view", "index/index");
		
		
		return "template/layout";
	}
}
