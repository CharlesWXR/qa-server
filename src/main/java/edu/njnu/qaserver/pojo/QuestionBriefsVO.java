package edu.njnu.qaserver.pojo;

import lombok.Data;

import java.util.List;

@Data
public class QuestionBriefsVO {
	private List<QuestionBriefVO> questions;
	private long page_count;
	private long total_count;
}
