package com.loafarm.noticepost;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loafarm.freepost.model.Page;
import com.loafarm.noticepost.bo.NoticePostBO;
import com.loafarm.noticepost.model.NoticePost;

@RequestMapping("/notice")
@Controller
public class NoticePostController {
	
	@Autowired
	private NoticePostBO noticePostBO;
	
	@GetMapping("/notice_create_view")
	public String noticeCreateView(Model model) {
		model.addAttribute("view", "notice/noticeCreate");
		return "template/layout";
	}
	
	@GetMapping("/notice_list_view")
	public String noticeListView(Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			HttpSession session) {
		// 비 로그인 시에도 게시물 목록을 보기위해 null 값 허용
		Integer userId = (Integer)session.getAttribute("userId");
		List<NoticePost> NoticePostList = noticePostBO.getNoticePostByLimit(page, userId);
		Page pageDTO = noticePostBO.pagingParam(page);
		model.addAttribute("NoticePostList", NoticePostList);
		model.addAttribute("paging", pageDTO);
		model.addAttribute("view", "notice/noticePost");
		return "template/layout";
	}
	
	@GetMapping("/notice_detail_view")
	public String noticeDetailView(Model model,
			@RequestParam("postId") int postId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		NoticePost noticePost = noticePostBO.getNoticePostByPostId(userId, postId);
		model.addAttribute("notice", noticePost);
		model.addAttribute("view", "notice/noticeDetail");
		return "template/layout";
	}
	
	@GetMapping("/notice_update_view")
	public String noticeUpdateView(Model model,
			@RequestParam("postId") int postId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		NoticePost noticePost = noticePostBO.getNoticePostByPostId(userId, postId);
		model.addAttribute("notice", noticePost);
		model.addAttribute("view", "notice/noticeUpdate");
		return "template/layout";
	}
}
