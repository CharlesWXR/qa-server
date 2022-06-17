package edu.njnu.qaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.njnu.qaserver.mapper.QuestionMapper;
import edu.njnu.qaserver.pojo.Question;
import edu.njnu.qaserver.pojo.QuestionBriefVO;
import edu.njnu.qaserver.pojo.QuestionBriefsVO;
import edu.njnu.qaserver.pojo.QuestionVO;
import edu.njnu.qaserver.service.QuestionService;
import edu.njnu.qaserver.service.SubjectService;
import edu.njnu.qaserver.utils.FileUploadUtil;
import edu.njnu.qaserver.utils.MinIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MinIOUtil MinIOUtil;

    private final int PageSize = 20;

    @Override
    public QuestionBriefsVO getAllQuestions(long page) {
        Page<Question> questionPage = questionMapper.selectPage(new Page<Question>(page, PageSize),
                null);
        return getBriefsFromPage(questionPage);
    }

    @Override
    public QuestionBriefsVO getQuestionsBySubjectName(String subjectName, long page) {
        int subjectID = subjectService.getSubjectIDByName(subjectName);

        QueryWrapper<Question> questionWrapper = new QueryWrapper<Question>();
        questionWrapper.eq("subject_id", subjectID);
        Page<Question> questionPage = questionMapper.selectPage(new Page<Question>(page, PageSize),
                questionWrapper);

        return getBriefsFromPage(questionPage);
    }

    @Override
    public QuestionBriefsVO getQuestionByUser(int userID) {
        QueryWrapper<Question> questionWrapper = new QueryWrapper<>();
        questionWrapper.eq("user_id", userID);
        List<Question> questionList = questionMapper.selectList(questionWrapper);

        return getBriefsFromList(questionList);
    }
    @Override
    public QuestionVO getQuestionByQuestionID(int questionID) {
        QueryWrapper<Question> questionWrapper = new QueryWrapper<>();
        questionWrapper.eq("question_id", questionID);
        Question question = questionMapper.selectOne(questionWrapper);

        question.setImg(MinIOUtil.getFileUrl(question.getImg()));

        QuestionVO result = new QuestionVO(question);
        result.setSubject_name(subjectService.getSubjectNameByID(question.getSubjectId()));

        return result;
    }

    private QuestionBriefsVO getBriefsFromPage(Page<Question> questionPage) {
        List<Question> questions = questionPage.getRecords();

        long totalPageCount = questionPage.getPages();
        long totalCount = questionPage.getTotal();
        List<QuestionBriefVO> briefs = questions.stream()
                .peek(t -> t.setImg(MinIOUtil.getFileUrl(t.getImg())))
                .map(QuestionBriefVO::new)
                .collect(Collectors.toList());

        QuestionBriefsVO res = new QuestionBriefsVO();
        res.setPage_count(totalPageCount);
        res.setTotal_count(totalCount);
        res.setQuestions(briefs);
        return res;
    }

    private QuestionBriefsVO getBriefsFromList(List<Question> questionList) {
        List<QuestionBriefVO> briefs = questionList.stream()
                .peek(img -> img.setImg(MinIOUtil.getFileUrl(img.getImg())))
                .map(t -> new QuestionBriefVO(t, subjectService.getSubjectNameByID(t.getSubjectId())))
                .collect(Collectors.toList());

        QuestionBriefsVO res = new QuestionBriefsVO();
        res.setQuestions(briefs);
        return res;
    }

    @Override
    public String putNewQuestion(String title, String mainContent,
                                 String subject, int credit,
                                 int userID, Object oFile)
            throws Exception {
        Question question = new Question();
        question.setTitle(title);
        question.setMainContent(mainContent);
        question.setCredit(credit);
        question.setUserId(userID);
        question.setSubjectId(subjectService.getSubjectIDByName(subject));
        question.setImg(null);

        String url = null;
        if (oFile != null) {
            MultipartFile file = (MultipartFile) oFile;
            String filepath = subject + "/" + FileUploadUtil.generateFileName(file.getOriginalFilename());
            url = MinIOUtil.uploadFile(file.getInputStream(), filepath, file.getContentType());
            question.setImg(filepath);
        }

        question.insert();
        return url;
    }
}
