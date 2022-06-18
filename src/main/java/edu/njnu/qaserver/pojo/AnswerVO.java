package edu.njnu.qaserver.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerVO {
	private Date time;
	private String main_content;
	private String img;
	private boolean std;
	private String username;

	public AnswerVO(Answer answer, String username) {
		this.time = answer.getTime();
		this.main_content = answer.getMainContent();
		this.img = answer.getImg();
		this.std = answer.isStd();
		this.username = username;
	}
}
