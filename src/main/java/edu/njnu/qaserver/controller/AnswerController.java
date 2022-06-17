package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@ResponseResult
	@RequestMapping(value = "/count/{id}", method = RequestMethod.GET)
	public long getAnswerCount(@PathVariable int id) {
		return answerService.getAnswerCountByQuestionID(id);
	}
}
