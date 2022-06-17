package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("user")
public class User extends Model<User> {
	@TableId(type = IdType.AUTO)
	private int userId;

	private String name;
	private String password;
	private String Email;
	private String phone;
	private String gender;
	private String home;
	private int privilege;
	private int classId;
}
