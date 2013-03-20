package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycelium.nbt.model.dao.ChangeRequestDao;
import com.mycelium.nbt.model.dao.IssueDao;

@Controller
@RequestMapping(value = "/analist")
public class AnalistController {
	Logger _logger = Logger.getLogger(AnalistController.class);

	@Autowired
	ChangeRequestDao _crDao;
	@Autowired
	IssueDao _issueDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAnalistPage() {
		ModelAndView mav = new ModelAndView("analist");
		mav.addObject("crs", _crDao.findAll());
		mav.addObject("issues", _issueDao.findAll());
		return mav;
	}
	@RequestMapping(value = "/issue/new", method = RequestMethod.GET)
	public ModelAndView createNewIssie() {
		return new ModelAndView("newIssue");
	}
	
	@RequestMapping(value = "/cr/new",method = RequestMethod.GET)
	public ModelAndView createNewCr() {
		return new ModelAndView("newCr");
	}
	@RequestMapping(value = "/cr/{crId}",method = RequestMethod.GET)
	public ModelAndView editCr(@PathVariable("crId") String changeRequestId) {
		return new ModelAndView("newCr");
	}
	
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public ModelAndView getIssue() {
		ModelAndView mav = new ModelAndView("analist");
		mav.addObject("issues", _issueDao.findAll());
		return mav;
	}
	
	/* @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addCrs(HttpServletRequest request) {
        String caption = request.getParameter("caption");
        String author = request.getParameter("author");
	     // TODO check parameters recieved
        
        CrEntity newCr = new CrEntity(caption, author);
        _crDao.addCRs(newCr);
        return "redirect:/site/analist"; 
    }*/
}