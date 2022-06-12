package edu.njnu.qaserver.pojo;

import lombok.Data;

@Data
public class QuestionBriefVO {
	private int question_id;
	private String title;
	private String content;
	private int like_count;
	private int ans_count;
	private String img_src;

	public QuestionBriefVO() {
	}

	public QuestionBriefVO(Question q) {
		this.question_id = q.getQuestionId();
		this.title = q.getTitle();
		this.content = q.getMainContent();
		this.img_src = q.getImg();
	}
}
