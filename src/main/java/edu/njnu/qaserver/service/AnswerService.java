package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.AnswerVO;

import java.util.List;

public interface AnswerService {
    List<AnswerVO> getAnswerByQuestionID(int questionID);

    long getAnswerCountByQuestionID(int questionID);
}
