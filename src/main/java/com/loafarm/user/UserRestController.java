package com.loafarm.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		Map<String, Object> result = new HashMap<>();
		
		// select
		User user = userBO.getUserByLoginId(loginId);
		if(user != null) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 500);
			result.put("result", false);
		}
		return result;
	}
	
	@RequestMapping("/is_duplicated_nickname")
	public Map<String, Object> isDuplicatedNickname(
			@RequestParam("nickname") String nickname){
		Map<String, Object> result = new HashMap<>();
		
		// select
		User user = userBO.getUserByNickname(nickname);
		if(user != null) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 500);
			result.put("result", false);
		}
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("nickname") String nickname,
			@RequestParam("password") String password,
			@RequestParam("email") String email){
		// 비밀번호 암호화
		// bcrypt 단방향 해시 알고리즘이기 때문에 복호화가 불가능하다.
		String encodedPassword = passwordEncoder.encode(password);
		
		Map<String, Object> result = new HashMap<>();
		
		// insert
		int rowCount = userBO.addUser(loginId, nickname, encodedPassword, email);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		User user = userBO.getUserByLoginId(loginId);
		
		if(user == null) {
			result.put("code", 501);
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}
		if(user != null) {	// matches => 비밀번호 값이 일치하는지 비교
			boolean check = passwordEncoder.matches(password, user.getPassword());
			if(check == true) {
				result.put("code", 1);
				result.put("result", "성공");
				// 로그인 할때마다 비밀번호 새로 암호화 및 업데이트
				// db에서 직접 업데이트를 하면 서버에 과부하가 올 수 있기때문에
				// 원래는 캐싱을 써야하지만 어려우므로 보류
				String encodedPassword = passwordEncoder.encode(password);
				userBO.updateUserByPassword(loginId, encodedPassword);
				session.setAttribute("userLoginId", user.getLoginId());
				session.setAttribute("userNickname", user.getNickname());
				session.setAttribute("userId", user.getId());
			} else {
				result.put("code", 500);
				result.put("errorMessage", "비밀번호가 일치하지 않습니다.");
			}
		}
		return result;
	}
}
