package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {
	List<Tag> getTagsBySubjectID(int id);

	Map<Integer, List<Tag>> getTags();
}
