package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.pojo.*;
import edu.njnu.qaserver.service.AnswerService;
import edu.njnu.qaserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

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
    @RequestMapping(value = "/id/{questionID}", method = RequestMethod.GET)
    public QuestionVO getQuestionByQuestionID(@PathVariable int questionID) {
        return questionService.getQuestionByQuestionID(questionID);
    }


    @ResponseResult
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public List<SubjectQuestionStat> getQuestionStats() {
        return questionService.getQuestionStats();
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
        String tagRaw = request.getParameter("tags");
        List<Integer> tags = null;
        if (tagRaw != null && tagRaw.length() > 0) {
            String[] tagStrings = tagRaw.split(",");
            tags = Arrays.stream(tagStrings)
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
        }


        Map<String, Object> res = new HashMap<String, Object>();
        res.put("url",
                questionService.putNewQuestion(title, mainContent, subject,
                        credit, userID, tags, file));
        return res;
    }

    @ResponseResult
    @RequestMapping(value = "/s", method = RequestMethod.GET)
    public QuestionBriefsVO searchQuestion(@RequestParam String content,
                                           @RequestParam String subjectName,
                                           @RequestParam List<Integer> tags,
                                           @RequestParam long page) {
        if (tags != null && tags.size() > 0)
            return questionService.searchQuestion(content, subjectName, tags, page);
        else
            return questionService.searchQuestion(content, subjectName, page);
    }

    @ResponseResult
    @RequestMapping(value = "/c", method = RequestMethod.GET)
    public List<String> completeQuestion(@RequestParam String content,
                                         @RequestParam String subjectName,
                                         @RequestParam List<Integer> tags) {
        if (tags != null && tags.size() > 0)
            return questionService.completeQuestion(content, subjectName, tags);
        else
            return questionService.completeQuestion(content, subjectName);
    }

    @ResponseResult
    @RequestMapping(value = "/answer/{questionID}", method = RequestMethod.GET)
    public List<AnswerVO> getAnswerByQuestionID(@PathVariable("questionID") int questionID) {
        return answerService.getAnswerByQuestionID(questionID);
    }

    @ResponseResult
    @RequestMapping(value = "/newAnswer/{questionID}", method = RequestMethod.PUT)
    public Map<String, Object> putNewAnswer(HttpServletRequest request,
                                            @PathVariable("questionID") int questionID) throws Exception {
        String mainContent = request.getParameter("mainContent");
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("img");
        int userID = Integer.parseInt(request.getParameter("user_id"));

        Map<String, Object> res = new HashMap<>();
        res.put("url", answerService.putNewAnswer(questionID, mainContent, userID, file));

        return res;
    }
}
