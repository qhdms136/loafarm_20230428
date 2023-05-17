package com.loafarm.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.comment.dao.CommentMapper;
import com.loafarm.comment.model.Comment;
import com.loafarm.comment.model.CommentView;
import com.loafarm.user.bo.UserBO;
import com.loafarm.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public int addComment(int postId, int userId, String content, String type) {
		return commentMapper.insertComment(postId, userId, content, type);
	}
	
	public void deleteComment(int commentId) {
		commentMapper.deleteComment(commentId);
	}
	
	public void deleteCommentByPostIdType(int postId, String type) {
		commentMapper.deleteCommentByPostIdType(postId, type);
	}
	
	// 댓글 리스트 가공
	// input : postId, type
	// output : 해당 게시물의 댓글 리스트
	public List<CommentView> generateCommentList(int postId, String type){
		List<CommentView> commentViewList = new ArrayList<>(); // 새로 담을 댓글 리스트
		List<Comment> commentList = commentMapper.selctCommentListByPostIdType(postId, type);
		
		// 반복문 Comment => CommentView 리스트에 담기
		for(Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 하나
			commentView.setComment(comment);
			
			// 댓글 쓴이
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
}
