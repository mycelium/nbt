package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
	Logger _logger = Logger.getLogger(ProfileController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getProfilePage() {
		return new ModelAndView("profile");
	}
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ModelAndView changeSettings() {
		return new ModelAndView("settings");
	}

}
