package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getAllGrades(int userID);
}
