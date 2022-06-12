package edu.njnu.qaserver.pojo;

import lombok.Data;

@Data
public class UserLoginVO {
	private int user_id;
	private String user_name;

	public UserLoginVO(User user) {
		this.user_id = user.getUserId();
		this.user_name = user.getName();
	}
}
