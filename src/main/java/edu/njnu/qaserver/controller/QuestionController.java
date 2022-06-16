package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.pojo.QuestionBriefsVO;
import edu.njnu.qaserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @ResponseResult
    @RequestMapping(value = "", method = RequestMethod.GET)
    public QuestionBriefsVO getQuestionByPage(@RequestParam("page") long page,
                                              @RequestParam("subject_name") String subjectName) {
        if (subjectName.equals("全部"))
            return questionService.getAllQuestions(page);
        else
            return questionService.getQuestionsBySubjectName(subjectName, page);
    }

    @ResponseResult
    @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    public QuestionBriefsVO getQuestionByUser(@PathVariable int userID) {
        return questionService.getQuestionByUser(userID);
    }

    @ResponseResult
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Map<String, Object> putNewQuestion(HttpServletRequest request) throws Exception {
        String title = request.getParameter("title");
        String mainContent = request.getParameter("main_content");
        String subject = request.getParameter("subject");
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("img");
        int credit = Integer.parseInt(request.getParameter("credit"));
        int userID = Integer.parseInt(request.getParameter("user_id"));

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("url", questionService.putNewQuestion(title, mainContent, subject, credit, userID, file));
        return res;
    }
}
