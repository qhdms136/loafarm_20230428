package com.loafarm.index;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loafarm.freepost.bo.FreePostBO;
import com.loafarm.freepost.model.FreePostView;

@RequestMapping("/index")
@Controller
public class IndexController {
	
	@Autowired
	private FreePostBO freePostBO;
	
	@GetMapping("/index_view")
	public String indexView(Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		List<FreePostView> freePostViewList = freePostBO.generateFreePostTodayBestViewList(userId);
		model.addAttribute("freePostList", freePostViewList);
		model.addAttribute("view", "index/index");
	
		return "template/layout";
	}
}
