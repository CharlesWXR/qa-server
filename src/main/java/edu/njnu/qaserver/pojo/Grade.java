package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("grade")
public class Grade extends Model<Grade> {
	@TableId(type = IdType.AUTO)
	private int gradeId;

	private int id;
}
