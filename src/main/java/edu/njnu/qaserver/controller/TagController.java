package edu.njnu.qaserver.controller;

import edu.njnu.qaserver.annotation.ResponseResult;
import edu.njnu.qaserver.pojo.TagVO;
import edu.njnu.qaserver.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired
	private TagService tagService;

	@ResponseResult
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Map<String, List<TagVO>> getAllTags() {
		return tagService.getTags();
	}
}
