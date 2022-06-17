package edu.njnu.qaserver.service;

import edu.njnu.qaserver.pojo.QuestionBriefsVO;
import edu.njnu.qaserver.pojo.SubjectQuestionStat;

import java.util.List;

public interface QuestionService {
	QuestionBriefsVO getAllQuestions(long page);

	QuestionBriefsVO getQuestionsBySubjectName(String subjectName, long page);

	QuestionBriefsVO getQuestionByUser(int userID);

	QuestionBriefsVO searchQuestion(String target, String subjectName, long page);
	QuestionBriefsVO searchQuestion(String target, String subjectName, List<Integer> tagIDs, long page);

	List<String> completeQuestion(String target, String subjectName);
	List<String> completeQuestion(String target, String subjectName, List<Integer> tagIDs);

	String putNewQuestion(String title, String mainContent,
	                      String subject, int credit,
	                      int userID, Object oFile) throws Exception;

	List<SubjectQuestionStat> getQuestionStats();
}
