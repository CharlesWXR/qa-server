package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.Tag;
import edu.njnu.qaserver.pojo.TagVO;

import java.util.List;
import java.util.Map;

public interface TagService {
	List<Tag> getTagsBySubjectID(int id);
	List<Tag> getTagsByQuestionID(int id);

	Map<String, List<TagVO>> getTags();
}
