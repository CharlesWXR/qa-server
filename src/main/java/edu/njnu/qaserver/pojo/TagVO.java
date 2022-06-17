package edu.njnu.qaserver.pojo;

import lombok.Data;

@Data
public class TagVO {
	private int tag_id;
	private String name;

	public TagVO(Tag t) {
		this.tag_id = t.getTagId();
		this.name = t.getName();
	}
}
