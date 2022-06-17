package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.UserLoginVO;

public interface UserService {
    UserLoginVO login(String userName, String password);

    Object register(String name, String password);

    Object editProfile(int userID, String name, String email, String phone, String gender, String home);
}
