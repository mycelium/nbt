package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycelium.nbt.model.dao.UserDao;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	Logger _logger = Logger.getLogger(UserController.class);

	@Autowired
	UserDao _userDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getProfilePage() {
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", _userDao.findAll());
		return mav;
	}
}
