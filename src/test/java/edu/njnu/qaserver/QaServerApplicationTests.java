package edu.njnu.qaserver;

import edu.njnu.qaserver.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QaServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("zby");
		user.setPassword("123456");

		boolean res = user.insert();
		System.out.println(res);
	}
}
