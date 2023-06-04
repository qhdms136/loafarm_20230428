package com.loafarm.noticepost.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loafarm.freepost.model.Page;
import com.loafarm.noticepost.dao.NoticePostMapper;
import com.loafarm.noticepost.model.NoticePost;

@Service
public class NoticePostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticePostMapper noticePostMapper;
	
	// 클래스 변수 (상수 불변)
		private static final int PAGE_LIMIT = 10; // 한 페이지당 보여줄 글 갯수
		private static final int BLOCK_LIMIT = 10; // 하단에 보여줄 페이지 번호 갯수
	
	public void addNoticePost(int userId, String subject, String content) {
		logger.info("[userId:{}, subject:{}, content:{}]", userId, subject, content);
		noticePostMapper.insertNoticePost(userId, subject, content);
	}
	
	// 공지사항 목록 페이지
	public List<NoticePost> getNoticePostByLimit(int page, Integer userId){
		// 페이징 처리
		int pagingStart = (page -1) * PAGE_LIMIT;
		return noticePostMapper.selectNoticePostListLimit(pagingStart, PAGE_LIMIT);
	}
		
	
	
	// 공지사항 페이징 변수처리
	public Page pagingParam(int page) {
		  // 카테고리 별 전체 글 갯수 조회
		
      int boardCount = noticePostMapper.selectNoticePostListCount();        		
      // 전체 페이지 갯수 계산(ex ) 10/3=3.33333 => 4)
      int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
      // 시작 페이지 값 계산(1, 11, 21, 31, ~~~~)
      int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
      // 끝 페이지 값 계산(10, 20, 30, 40, ~~~~)
      int endPage = startPage + BLOCK_LIMIT - 1;
      if (endPage > maxPage) {
          endPage = maxPage;
      }
      Page pageDTO = new Page();
      pageDTO.setPage(page);
      pageDTO.setMaxPage(maxPage);
      pageDTO.setStartPage(startPage);
      pageDTO.setEndPage(endPage);
      return pageDTO;
	}
	
	
}
