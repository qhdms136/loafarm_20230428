package com.loafarm.freepost;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loafarm.freepost.bo.FreePostBO;
import com.loafarm.freepost.model.FreePost;
import com.loafarm.freepost.model.FreePostView;
import com.loafarm.freepost.model.Page;

@RequestMapping("/free")
@Controller
public class FreePostController {
	
	@Autowired
	private FreePostBO freePostBO;
	
	@GetMapping("/free_list_view")
	public String freeListView(Model model,
			@RequestParam(value="category", required=false) String category,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			HttpSession session) {
		// 비 로그인 시에도 게시물 목록을 보기위해 null값 허용
		Integer userId = (Integer)session.getAttribute("userId");
		List<FreePostView> freePostViewList = freePostBO.generateFreePostViewList(userId, category, page);
		// 페이지 계산
		Page pageDTO = freePostBO.pagingParam(page);
		
		// 해당 페이지에서 보여줄 글 목록
		model.addAttribute("freePostList", freePostViewList);
		model.addAttribute("view", "free/freePost");
		model.addAttribute("paging",pageDTO);
		System.out.println("page = " + page);
		System.out.println("freePostViewList = " + freePostViewList);
		return "template/layout";
	}
	
	@GetMapping("/free_list_view_recommend")
	public String freeListRecommendCountView(Model model,
			@RequestParam("recommendCount") int recommendCount,
			HttpSession session) {
		// 비로그인 시에도 게시물 목록 보기 가능
		Integer userId = (Integer)session.getAttribute("userId");
		List<FreePostView> freePostViewList = freePostBO.generateFreePostRecommendViewList(userId, recommendCount);
		model.addAttribute("freePostList", freePostViewList);
		model.addAttribute("view", "free/freePost");
		return "template/layout";
	}
	
	@GetMapping("/free_detail_view")
	public String freeDetailView(Model model,
			@RequestParam("freePostId") int freePostId,
			@RequestParam(value="type", required=false) String type,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		FreePostView freePostView = freePostBO.generateFreePostView(freePostId, userId, type);
		model.addAttribute("freePostView", freePostView);
		model.addAttribute("view", "free/freeDetail");
		return "template/layout";
	}
	
	@GetMapping("/free_create_view")
	public String freeCreateView(Model model) {
		model.addAttribute("view", "free/freeCreate");
		return "template/layout";
	}
	
	@GetMapping("/free_update_view")
	public String freeUpdateView(Model model,
			@RequestParam("freePostId") int freePostId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		FreePost freePost = freePostBO.getFreePostByPostIdUserId(freePostId, userId);
		model.addAttribute("freePost", freePost);
		model.addAttribute("view", "free/freeUpdate");
		return "template/layout";
	}
}
