package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "answer")
public class AnswerVO {
    @TableId(type = IdType.AUTO)
    private int answerId;

    private Date time;
    private String mainContent;
    private String img;
    private boolean std;
    private int userId;
    private int questionId;
}
