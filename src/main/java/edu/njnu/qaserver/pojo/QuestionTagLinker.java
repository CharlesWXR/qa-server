package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("question_tag_linker")
public class QuestionTagLinker extends Model<QuestionTagLinker> {
	private int questionId;
	private int tagId;
}
