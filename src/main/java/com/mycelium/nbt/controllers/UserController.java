package com.mycelium.nbt.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mycelium.nbt.model.dao.RoleDao;
import com.mycelium.nbt.model.dao.UserDao;
import com.mycelium.nbt.model.entities.UserEntity;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    Logger _logger = Logger.getLogger(UserController.class);

    @Autowired
    UserDao _userDao;
    @Autowired
    RoleDao _roleDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUsers() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", _userDao.findAll());
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        String login = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // TODO check parameters recieved
        if (_userDao.findByLogin(login) != null) {
            _logger.warn("User with login = " + login + "already exists");
            return "redirect:/site/user";
        }
		_logger.warn("User with login = " + login + "already exists");
        UserEntity newUser = new UserEntity(login, /*_roleDao.findByName("user").getId()*/ "chacha", email, firstName, lastName,
                password);
        _userDao.addUser(newUser);
        return "redirect:/site/user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable("id") String id) {
        _logger.info("request for delete user with id = " + id);
        UserEntity deletedUser = _userDao.deleteUser(id);
        _logger.info("Success delete user = " + deletedUser);
        return "redirect:/site/user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getProfile(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("userProfile", _userDao.findOne(id));
        return mav;
    }

}
