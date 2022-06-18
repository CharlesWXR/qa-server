package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "answer")
public class Answer extends Model<Answer> {
    @TableId(type = IdType.AUTO)
    private int answerId;

    private Date time;
    private String mainContent;
    private String img;
    private int userId;
    private int questionId;
    private boolean std;
}
