package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.Answer;
import edu.njnu.qaserver.pojo.AnswerVO;

import java.util.List;

public interface AnswerService {
    List<AnswerVO> getAnswerByQuestionID(int questionID);

    String putNewAnswer(int questionID, String mainContent,int userID,Object oFIle) throws Exception;

    long getAnswerCountByQuestionID(int questionID);
}
