package com.loafarm.custompost;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loafarm.custompost.bo.CustomPostBO;
import com.loafarm.custompost.model.CustomPost;
import com.loafarm.custompost.model.CustomPostView;
import com.loafarm.freepost.model.Page;

@RequestMapping("/custom")
@Controller
public class CustomPostController {
	
	@Autowired
	private CustomPostBO customPostBO;
	
	@GetMapping("/custom_list_view")
	public String customListView(Model model,
			HttpSession session) {
		// 비 로그인 시에도 게시물 목록을 보기위해 null값 허용
		Integer userId = (Integer)session.getAttribute("userId");
		List<CustomPostView> customPostViewList = customPostBO.generateCustomPostViewList(userId);
		model.addAttribute("customPostList", customPostViewList);
		model.addAttribute("view", "custom/customPost");
		return "template/layout";
	}
	
	@GetMapping("/custom_more_list_view")
	public String customMoreListView(Model model,
			@RequestParam("cnt") int cnt,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		List<CustomPostView> customPostViewList = customPostBO.generateCustomPostMoreViewList(userId, cnt);
		model.addAttribute("customPostList", customPostViewList);
		return "custom/customMoreList";
	}
	
	@GetMapping("/mycustom_view")
	public String myCustomView(Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		List<CustomPost> customPosList = customPostBO.getCustomPostBydUserId(page, userId);
		Page pageDTO = customPostBO.pagingParam(page, userId);
		model.addAttribute("customPosList", customPosList);
		model.addAttribute("paging", pageDTO);
		model.addAttribute("view", "custom/mycustomPost");
		return "template/layout";
	}
	
	/*
	 * @GetMapping("/custom_list_view_recommend") public String
	 * customListViewRecommend(Model model, HttpSession session) { Integer userId =
	 * (Integer)session.getAttribute("userId"); List<CustomPostView>
	 * customPostViewList =
	 * customPostBO.generateCustomPostRecommendViewList(userId);
	 * model.addAttribute("customPostList", customPostViewList);
	 * model.addAttribute("view", "custom/customPost"); return "template/layout"; }
	 */
	
	
	@GetMapping("/custom_create_view")
	public String customCreateView(Model model) {
		model.addAttribute("view", "custom/customCreate");
		return "template/layout";
	}
	
	@GetMapping("/custom_detail_view")
	public String customDetailView(Model model,
			@RequestParam("customPostId") int customPostId,
			@RequestParam("type") String type,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		CustomPostView customPostView = customPostBO.generateCustomPostView(customPostId, userId, type);
		model.addAttribute("customPostView", customPostView);
		model.addAttribute("view", "custom/customDetail");
		return "template/layout";
	}
	
	@GetMapping("/custom_update_view")
	public String customUpdateView(Model model,
			@RequestParam("customPostId") int customPostId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		CustomPost customPost = customPostBO.getCustomPostByPostIdUserId(customPostId, userId);
		model.addAttribute("customPost", customPost);
		model.addAttribute("view", "custom/customUpdate");
		return "template/layout";
	}
}
