package edu.njnu.qaserver.service.impl;

import edu.njnu.qaserver.mapper.TagMapper;
import edu.njnu.qaserver.pojo.Tag;
import edu.njnu.qaserver.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagMapper tagMapper;

	Map<Integer, List<Tag>> subjectTagMapper = null;

	@Override
	public List<Tag> getTagsBySubjectID(int id) {
		if (subjectTagMapper == null) {
			loadTags();
		}
		return subjectTagMapper.get(id);
	}

	@Override
	public Map<Integer, List<Tag>> getTags() {
		return subjectTagMapper;
	}

	private void loadTags() {
		List<Tag> tags = tagMapper.selectList(null);
		for (Tag tag : tags) {
			int key = tag.getSubjectId();
			List<Tag> tar = subjectTagMapper.get(key);
			if (tar == null) {
				tar = new ArrayList<Tag>();
				subjectTagMapper.put(key, tar);
			}
			tar.add(tag);
		}
	}
}
