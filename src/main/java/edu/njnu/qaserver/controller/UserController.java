package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.pojo.UserLoginVO;
import edu.njnu.qaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseResult
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserLoginVO login(@RequestBody Map<String, String> params) throws Exception {
        if (!params.containsKey("username"))
            throw new MissingServletRequestParameterException("username", "String");
        if (!params.containsKey("password"))
            throw new MissingServletRequestParameterException("password", "String");

        String userName = params.get("username");
        String password = params.get("password");
        return userService.login(userName, password);
    }

    @ResponseResult
    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public Map<String, Object> register(@RequestBody Map<String, String> params) throws Exception {
        String username = params.get("name");
        String password = params.get("password");

        Map<String, Object> res = new HashMap<>();
        res.put("new_user", userService.register(username, password));
        return res;
    }

    @ResponseResult
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Map<String, Object> editProfile(@RequestBody Map<String, String> params) throws Exception {
        int user_id = Integer.parseInt(params.get("userID"));
        String name = params.get("name");
        String email = params.get("email");
        String phone = params.get("phone");
        String gender = params.get("gender");
        String home = params.get("home");

        Map<String, Object> res = new HashMap<>();
        res.put("edit_profile", userService.editProfile(user_id, name, email, phone, gender, home));
        return res;
    }
}
