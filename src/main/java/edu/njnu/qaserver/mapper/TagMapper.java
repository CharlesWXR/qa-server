package edu.njnu.qaserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.njnu.qaserver.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
	List<Tag> getTagsByQuestionID(int questionID);
}
