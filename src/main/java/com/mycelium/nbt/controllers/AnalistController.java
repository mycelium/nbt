package com.mycelium.nbt.controllers;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.mycelium.nbt.model.dao.CrDao;
import com.mycelium.nbt.model.dao.RoleDao;
import com.mycelium.nbt.model.dao.UserDao;
import com.mycelium.nbt.model.entities.CrEntity;

@Controller
@RequestMapping(value = "/analist")
public class AnalistController {
	Logger _logger = Logger.getLogger(AnalistController.class);

	@Autowired
	CrDao _crDao;
	@Autowired
	RoleDao _roleDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCr() {
		ModelAndView mav = new ModelAndView("analist");
		mav.addObject("crs", _crDao.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addCrs(HttpServletRequest request) {
        String caption = request.getParameter("caption");
        String author = request.getParameter("author");
	     // TODO check parameters recieved
        
        CrEntity newCr = new CrEntity(caption, author);
        _crDao.addCRs(newCr);
        return "redirect:/site/analist";
    }
}