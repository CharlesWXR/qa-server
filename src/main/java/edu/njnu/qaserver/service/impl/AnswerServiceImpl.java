package edu.njnu.qaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.njnu.qaserver.mapper.AnswerMapper;
import edu.njnu.qaserver.pojo.AnswerVO;
import edu.njnu.qaserver.service.AnswerService;
import edu.njnu.qaserver.utils.FileUploadUtil;
import edu.njnu.qaserver.utils.MinIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    MinIOUtil MinIOUtil;

    @Override
    public List<AnswerVO> getAnswerByQuestionID(int questionID) {
        QueryWrapper<AnswerVO> answerWrapper = new QueryWrapper<>();
        answerWrapper.eq("question_id", questionID);
        List<AnswerVO> answerList = answerMapper.selectList(answerWrapper).stream()
                .peek(img -> img.setImg(MinIOUtil.getFileUrl(img.getImg())))
                .collect(Collectors.toList());

        return answerList;
    }

    @Override
    public long getAnswerCountByQuestionID(int questionID) {
        QueryWrapper<AnswerVO> answerWrapper = new QueryWrapper<AnswerVO>();
        answerWrapper.eq("question_id", questionID);

        return answerMapper.selectCount(answerWrapper);
    }

    @Override
    public String putNewAnswer(int questionID, String mainContent, int userID, Object oFile) throws Exception {
        AnswerVO answer = new AnswerVO();
        answer.setQuestionId(questionID);
        answer.setMainContent(mainContent);
        answer.setUserId(userID);

        String url = null;
        if (oFile != null) {
            MultipartFile file = (MultipartFile) oFile;
            String filepath = Integer.toString(questionID) + "/Answer" + FileUploadUtil.generateFileName(file.getOriginalFilename());
            url = MinIOUtil.uploadFile(file.getInputStream(), filepath, file.getContentType());
            answer.setImg(filepath);
        }

        answer.insert();

        return url;
    }

}
