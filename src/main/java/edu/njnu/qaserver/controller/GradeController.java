package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.pojo.Grade;
import edu.njnu.qaserver.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/MyGrade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @ResponseResult
    @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    public List<Grade> getAllGrades(@PathVariable int userID) {
        return gradeService.getAllGrades(userID);
    }

}
