package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
@TableName("question")
public class Question extends Model<Question> {
	@TableId(type = IdType.AUTO)
	private int questionId;

	private Date time;
	private String mainContent;
	private String title;
	private String img;
	private int credit;

	private int userId;
	private int subjectId;
}
