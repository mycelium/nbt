package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/help")
public class HelpController {
	Logger _logger = Logger.getLogger(HelpController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHelpPage() {
		return new ModelAndView("help");
	}
	}