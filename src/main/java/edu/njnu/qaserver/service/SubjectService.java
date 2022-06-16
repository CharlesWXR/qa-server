package edu.njnu.qaserver.service;

public interface SubjectService {
	int getSubjectIDByName(String name);

	String getSubjectNameByID(int ID);
}
