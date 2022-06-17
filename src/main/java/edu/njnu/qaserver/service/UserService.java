package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.UserLoginVO;

public interface UserService {
	UserLoginVO login(String userName, String password);

	Object register(String name, String password);
}
