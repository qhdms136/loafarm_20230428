package com.loafarm.freepost;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loafarm.freepost.bo.FreePostBO;
import com.loafarm.freepost.model.FreePostView;

@RequestMapping("/free")
@Controller
public class FreePostController {
	
	@Autowired
	private FreePostBO freePostBO;
	
	@GetMapping("/free_list_view")
	public String freeListView(Model model,
			HttpSession session) {
		// 비 로그인 시에도 게시물 목록을 보기위에 null값 허용
		Integer userId = (Integer)session.getAttribute("userId");
		List<FreePostView> freePostViewList = freePostBO.generateFreePostViewList(userId);
		model.addAttribute("freeViewList", freePostViewList);
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
