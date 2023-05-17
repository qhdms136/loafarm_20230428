package com.loafarm.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.loafarm.user.model.User;

@SpringBootTest // spring 수행
class UserBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserBO userBO;
	
	@Transactional // insert 후 다시 롤백
	//@Test
	void 회원가입() {
		logger.info("### 회원가입 ###");
		userBO.addUser("test999","test999" , "test999", "tset999@naver.com");
	}
	
	//@Test
	void 유저가져오기() {
		logger.info("### 유저 가져오기 ###");
		User user = userBO.getUserByLoginId("aaaa");
		assertNotNull(user);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
