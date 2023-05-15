package com.loafarm.recommend.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.recommend.dao.RecommendMapper;

@Service
public class RecommendBO {
	
	@Autowired
	private RecommendMapper recommendMapper;
	
	public void recommendToggle(int userId, int postId, String type) {
		int result = recommendMapper.selectRecommendCountByPostIdUserIdType(userId, postId, type);
		if(result > 0) {	// 있으면 제거
			recommendMapper.deleteByPostIdUserIdType(userId, postId, type);
		} else {	// 없으면 추가
			recommendMapper.insertByPostIdUserIdType(userId, postId, type);
		}
	}
	
	public boolean existRecommend(int userId, int postId, String type) {
		return recommendMapper.selectRecommendCountByPostIdUserIdType(userId, postId, type) > 0;
	}
	
	public int selectRecommendCountByPostIdType(int postId, String type) {
		return recommendMapper.selectRecommendCountByPostIdType(postId, type);
	}
	
}
