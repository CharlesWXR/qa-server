package edu.njnu.qaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.njnu.qaserver.mapper.GradeMapper;
import edu.njnu.qaserver.pojo.Grade;
import edu.njnu.qaserver.service.GradeService;
import edu.njnu.qaserver.utils.MinIOUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> getAllGrades(int userID) {
        QueryWrapper<Grade> gradeWrapper = new QueryWrapper<>();
        gradeWrapper.eq("user_id", userID);
        List<Grade> gradeList = gradeMapper.selectList(gradeWrapper);

        return gradeList;
    }

}
