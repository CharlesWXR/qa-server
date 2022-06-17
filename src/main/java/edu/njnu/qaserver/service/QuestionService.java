package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.Question;
import edu.njnu.qaserver.pojo.QuestionBriefsVO;
import edu.njnu.qaserver.pojo.SubjectQuestionStat;

import java.util.List;

public interface QuestionService {
	QuestionBriefsVO getAllQuestions(long page);

	QuestionBriefsVO getQuestionsBySubjectName(String subjectName, long page);

	QuestionBriefsVO getQuestionByUser(int userID);

	String putNewQuestion(String title, String mainContent,
	                      String subject, int credit,
	                      int userID, Object oFile) throws Exception;

	List<SubjectQuestionStat> getQuestionStats();
}
