package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.QuestionBriefsVO;

public interface QuestionService {
	QuestionBriefsVO getAllQuestions(long page);

	QuestionBriefsVO getQuestionsBySubjectName(String subjectName, long page);

	String putNewQuestion(String title, String mainContent,
	                      String subject, int credit,
	                      int userID, Object oFile) throws Exception;
}
