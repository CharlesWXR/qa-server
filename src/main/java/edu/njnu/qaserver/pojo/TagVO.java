package edu.njnu.qaserver.pojo;

import lombok.Data;

@Data
public class TagVO {
	private int value;
	private String label;

	public TagVO(Tag t) {
		this.value = t.getTagId();
		this.label = t.getName();
	}
}
