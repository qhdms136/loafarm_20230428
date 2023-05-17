package com.loafarm.freepost.bo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class FreePostBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FreePostBO freePostBO;
	
	@Transactional // 롤백
	@Test
	void 게시물삭제() {
		logger.info("#### 게시물 삭제 ####");
		freePostBO.deletePostByPostIdUserId(6, 3);
	}
}
