package edu.njnu.qaserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.njnu.qaserver.pojo.Question;
import edu.njnu.qaserver.pojo.SubjectQuestionStat;

import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {
	List<SubjectQuestionStat> getQuestionCounts();

	Page<Question> searchQuestion(IPage<Question> page, String target, List<Integer> tagIDs);
}
