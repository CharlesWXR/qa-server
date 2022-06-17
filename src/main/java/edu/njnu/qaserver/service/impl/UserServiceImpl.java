package edu.njnu.qaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.njnu.qaserver.mapper.UserMapper;
import edu.njnu.qaserver.pojo.User;
import edu.njnu.qaserver.pojo.UserLoginVO;
import edu.njnu.qaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserLoginVO login(String userName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", userName);
        queryWrapper.eq("password", password);

        User user = userMapper.selectOne(queryWrapper);
        if (user == null)
            return null;
        else
            return new UserLoginVO(user);
    }

    @Override
    public Object register(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.insert();
        return user;
    }
}
