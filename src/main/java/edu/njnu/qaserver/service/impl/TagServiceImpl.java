package edu.njnu.qaserver.service.impl;

import edu.njnu.qaserver.mapper.TagMapper;
import edu.njnu.qaserver.pojo.Tag;
import edu.njnu.qaserver.pojo.TagVO;
import edu.njnu.qaserver.service.SubjectService;
import edu.njnu.qaserver.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private SubjectService subjectService;

	private Map<Integer, List<Tag>> subjectTagMapper = null;
	private Map<String, List<Tag>> subjectNameTagMapper = null;

	@Override
	public List<Tag> getTagsBySubjectID(int id) {
		if (subjectTagMapper == null) {
			loadTags();
		}
		return subjectTagMapper.get(id);
	}

	@Override
	public List<Tag> getTagsByQuestionID(int id) {
		return tagMapper.getTagsByQuestionID(id);
	}

	@Override
	public Map<String, List<TagVO>> getTags() {
		if (subjectNameTagMapper == null) {
			loadTags();
		}

		Map<String, List<TagVO>> res = new HashMap<String, List<TagVO>>();
		subjectNameTagMapper.forEach((k, v) -> {
			res.put(k, v.stream().map(TagVO::new).collect(Collectors.toList()));
		});
		return res;
	}

	private void loadTags() {
		List<Tag> tags = tagMapper.selectList(null);

		subjectTagMapper = new HashMap<Integer, List<Tag>>();
		for (Tag tag : tags) {
			int key = tag.getSubjectId();
			List<Tag> tar = subjectTagMapper.get(key);
			if (tar == null) {
				tar = new ArrayList<Tag>();
				subjectTagMapper.put(key, tar);
			}
			tar.add(tag);
		}

		subjectNameTagMapper = new HashMap<String, List<Tag>>();
		for (Map.Entry<Integer, List<Tag>> entry : subjectTagMapper.entrySet()) {
			subjectNameTagMapper.put(
					subjectService.getSubjectNameByID(entry.getKey()),
					entry.getValue()
			);
		}
	}
}
