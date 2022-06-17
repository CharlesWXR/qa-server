package edu.njnu.qaserver.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionVO {
    private Date time;
    private String main_content;
    private String title;
    private String img;
    private int credit;
    private String subject_name;

    public QuestionVO(Question question) {
        this.time = question.getTime();
        this.main_content = question.getMainContent();
        this.title = question.getTitle();
        this.img=question.getImg();
        this.credit=question.getCredit();
    }
}
