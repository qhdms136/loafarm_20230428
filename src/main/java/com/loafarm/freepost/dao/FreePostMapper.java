package com.loafarm.freepost.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loafarm.freepost.model.FreePost;

@Repository
public interface FreePostMapper {
	public int insertFreePost(
			@Param("userId") int userId, 
			@Param("category") String category, 
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("type") String type,
			@Param("imagePath") String imagePath);
	
	public List<FreePost> selectFreePostList(
			@Param("pagingStart") int pagingStart,
			@Param("pageLimit") int pageLimit);
	
	// 전체 글 개수
	public int selectFreePostListCount();
	
	// 카테고리 별 개수
	public int selectFreePostListByCategoryCount(String category);
	
	// 추천 수(10,30) 이상 개수
	public int selectFreePostRecommendListByCount(int recommendCount);
	
	// 카테고리 별 목록
	public List<FreePost> selectFreePostListByCategory(
			@Param("category") String category,
			@Param("pagingStart") int pagingStart,
			@Param("pageLimit") int pageLimit);
	
	// 추천수에 따른 자유 게시판 목록
	public List<FreePost> selectFreePostListByRecommendCount(int recommendCount);
	
	// 내가 쓴 자유 게시판 목록
	public List<FreePost> selectFreePostListByUserId(int userId);
	
	// 내가 쓴 자유 게시판 목록 갯수
	public int selectFreePostListByUserIdCount(int userId);
	
	// 내가 쓴 자유 게시판 목록 limit
	public List<FreePost> selectFreePostListByUserIdLimit(
			@Param("userId") int userId,
			@Param("pagingStart") int pagingStart,
			@Param("pageLimit") int pageLimit);
	
	
	public FreePost selectFreePostById(int freePostId);
	
	public void updateFreePostByPostId(
			@Param("freePostId") int freePostId,
			@Param("category") String category,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public FreePost selectFreePostByPostIdUserId(
			@Param("freePostId") int freePostId,
			@Param("userId") int userId);
	
	public List<FreePost> selectFreePostListOrderByPostIdRecommendcount();
	
	public void updateRecommendCount(
			@Param("freePostId") int freePostId,
			@Param("type") String type,
			@Param("recommendCount") int recommendCount);
	
	public void deleteImageAndUpdateByPostIdUserId(
			@Param("freePostId") int freePostId,
			@Param("userId") int userId);
	
	public int deletePostByPostIdUserId(
			@Param("freePostId") int freePostId,
			@Param("userId") int userId);
}
