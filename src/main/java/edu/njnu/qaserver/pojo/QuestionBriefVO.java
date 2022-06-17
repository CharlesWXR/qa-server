package edu.njnu.qaserver.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionBriefVO {
	private int question_id;
	private String title;
	private String content;
	private int like_count;
	private int ans_count;
	private int credit;
	private String img_src;
	private Date time;
	private String subject_name;
	private List<SubjectTagPairVO> tags;

	public QuestionBriefVO() {
	}

	public QuestionBriefVO(Question q) {
		this.question_id = q.getQuestionId();
		this.title = q.getTitle();
		this.content = q.getMainContent();
		this.img_src = q.getImg();
		this.time = q.getTime();
		this.credit = q.getCredit();
	}

	public QuestionBriefVO(Question q, String subjectName) {
		this(q);
		this.subject_name = subjectName;
	}

	public QuestionBriefVO(Question q, List<SubjectTagPairVO> tags) {
		this(q);
		this.tags = tags;
	}
}
