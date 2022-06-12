package edu.njnu.qaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.njnu.qaserver.mapper.SubjectMapper;
import edu.njnu.qaserver.pojo.Subject;
import edu.njnu.qaserver.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectMapper subjectMapper;

	private Map<String, Integer> mapper = null;

	@Override
	public int getSubjectIDByName(String name) {
		if (this.mapper == null) {
			this.mapper = new HashMap<String, Integer>();

			List<Subject> subjects = this.subjectMapper.selectList(null);
			for (Subject subject : subjects) {
				this.mapper.put(subject.getName(), subject.getSubjectId());
			}
		}
		return this.mapper.get(name);
	}
}
