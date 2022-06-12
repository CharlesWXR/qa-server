package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.pojo.UserLoginVO;
import edu.njnu.qaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

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
}
