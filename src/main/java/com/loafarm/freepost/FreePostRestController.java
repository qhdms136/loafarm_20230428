package com.loafarm.freepost;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loafarm.freepost.bo.FreePostBO;

@RequestMapping("/free")
@RestController
public class FreePostRestController {
	
	@Autowired
	private FreePostBO freePostBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("category") String category,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("type") String type,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpSession session){
		
		// 세션에서 유저 정보 꺼내오기
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// insert
		int rowCount = freePostBO.addFreePost(userId, userLoginId, category, subject, content, type, file);
		
		Map<String, Object> result = new HashMap<>();
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "글을 저장하는데 실패하였습니다.");
		}
		return result;
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("freePostId") int freePostId,
			@RequestParam("category") String category,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpSession session){
		
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// update db
		freePostBO.updateFreePost(userId, userLoginId, freePostId, category, subject, content, file);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@PutMapping("/delete_image")
	public Map<String, Object> deleteImage(
			@RequestParam("freePostId") int freePostId,
			HttpSession session){
		int userId = (int)session.getAttribute("userId");
		
		// delete - image
		 freePostBO.deleteImageAndUpdateByPostIdUserId(freePostId, userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("freePostId") int freePostId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		int userId = (int)session.getAttribute("userId");
		
		// delete
		int rowCount = freePostBO.deletePostByPostIdUserId(freePostId, userId);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "게시물 삭제에 실패하였습니다.");
		}
		
		return result;
	}
}