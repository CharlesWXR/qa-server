package edu.njnu.qaserver.service.impl;

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
	private Map<Integer, String> reversedMapper = null;

	@Override
	public int getSubjectIDByName(String name) {
		if (this.mapper == null) {
			loadSubjects();
		}
		return this.mapper.get(name);
	}

	@Override
	public String getSubjectNameByID(int ID) {
		if (this.reversedMapper == null) {
			loadSubjects();
		}
		return this.reversedMapper.get(ID);
	}

	private void loadSubjects() {
		this.mapper = new HashMap<String, Integer>();
		this.reversedMapper = new HashMap<Integer, String>();

		List<Subject> subjects = this.subjectMapper.selectList(null);
		for (Subject subject : subjects) {
			this.mapper.put(subject.getName(), subject.getSubjectId());
			this.reversedMapper.put(subject.getSubjectId(), subject.getName());
		}
	}
}
