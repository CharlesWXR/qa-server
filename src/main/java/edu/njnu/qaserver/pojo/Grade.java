package edu.njnu.qaserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName(value = "grade")
public class Grade extends Model<Grade> {
    @TableId(value = "grade_id", type = IdType.AUTO)
    private int gradeId;

    @TableField(value = "value")
    private int value;

    @TableField(value = "user_id")
    private int userId;

    @TableField(value = "subject_id")
    private int subjectId;

}
