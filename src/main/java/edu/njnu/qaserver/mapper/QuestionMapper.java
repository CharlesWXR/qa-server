package edu.njnu.qaserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.njnu.qaserver.pojo.Question;
import edu.njnu.qaserver.pojo.SubjectQuestionStat;

import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {
	List<SubjectQuestionStat> getQuestionCounts();
}
