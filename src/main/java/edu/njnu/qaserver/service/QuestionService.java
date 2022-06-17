package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.QuestionBriefsVO;
import edu.njnu.qaserver.pojo.SubjectQuestionStat;

import java.util.List;

public interface QuestionService {
	QuestionBriefsVO getAllQuestions(long page);

	QuestionBriefsVO getQuestionsBySubjectName(String subjectName, long page);

	QuestionBriefsVO getQuestionByUser(int userID);

	QuestionBriefsVO searchQuestion(String target, long page);
	QuestionBriefsVO searchQuestion(String target, List<Integer> tagIDs, long page);

	String putNewQuestion(String title, String mainContent,
	                      String subject, int credit,
	                      int userID, Object oFile) throws Exception;

	List<SubjectQuestionStat> getQuestionStats();
}
