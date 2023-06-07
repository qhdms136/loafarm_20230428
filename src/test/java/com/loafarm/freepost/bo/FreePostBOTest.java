package com.loafarm.freepost.bo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FreePostBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FreePostBO freePostBO;
	
	/*
	 * @Transactional // 롤백
	 * 
	 * @Test void 게시물삭제() { logger.info("#### 게시물 삭제 ####");
	 * freePostBO.deletePostByPostIdUserId(6, 3); }
	 */
	
	/*
	 * @Test void 게시물추가() { logger.info("$$$$ 게시물 추가 $$$$");
	 * freePostBO.addFreePost(20, "zzzz", "기타", "줴이유닛테스뜨", "Junit~~~~~~~~~~~~~~",
	 * "free", null); }
	 */
		
		@Test void 게시물수정() {
			logger.info("@@@ 게시물 수정 @@@");
			freePostBO.updateFreePost(20, "zzzz", 23, "잡담", "줴이유닛테스뜨수정본", "JUnit~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", null);
		}
}
