package com.loafarm.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("view", "user/signUp");
		return "template/user";
	}
	
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("view", "user/signIn");
		return "template/user";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 로그아웃
		session.removeAttribute("userLoginId");
		session.removeAttribute("userNickname");
		session.removeAttribute("useId");
		return "redirect:/index/index_view";
	}
}
