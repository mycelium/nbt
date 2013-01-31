package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {
	Logger _logger = Logger.getLogger(IssueController.class);

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView createNewIssie() {
		return new ModelAndView("newIssue");
	}

}
