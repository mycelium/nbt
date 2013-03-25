package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import com.mycelium.nbt.model.dao.ChangeRequestDao;
import com.mycelium.nbt.model.dao.IssueDao;
import com.mycelium.nbt.model.entities.ChangeRequestEntity;
import com.mycelium.nbt.model.entities.IssueEntity;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.mycelium.nbt.controllers.UserController;
import com.mycelium.nbt.model.dao.UserDao;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/analist")
public class AnalistController {
	Logger _logger = Logger.getLogger(AnalistController.class);

	@Autowired
	ChangeRequestDao _crDao;
	@Autowired
	IssueDao _issueDao;
	@Autowired
    UserDao _userDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAnalistPage() {
		ModelAndView mav = new ModelAndView("analist");
		mav.addObject("crs", _crDao.findAll());
		mav.addObject("issues", _issueDao.findAll());
		return mav;
	}
	@RequestMapping(value = "/issue/new", method = RequestMethod.GET)
	public ModelAndView createNewIssie() {
		ModelAndView mav=new ModelAndView("newIssue");
		mav.addObject("users",_userDao.findAll());
		return mav;
	}
	@RequestMapping(value = "/cr/new",method = RequestMethod.GET)
	public ModelAndView createNewCr() {
		ModelAndView mav=new ModelAndView("newCr");
		mav.addObject("users",_userDao.findAll());
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
	public ModelAndView getIssueView(@PathVariable("id") String issueViewId) {
		ModelAndView mav=new ModelAndView("issue");
		mav.addObject("issueView", _issueDao.findOne(issueViewId));
		return mav;
	}
	@RequestMapping(value = "/cr/{id}",method = RequestMethod.GET)
	public ModelAndView getCrView(@PathVariable("id") String changeRequestId) {
		ModelAndView mav=new ModelAndView("cr");
		mav.addObject("crView", _crDao.findOne(changeRequestId));
		return mav;
	}
	
	
	
	//CR
	@RequestMapping(value = "/cr/add", method = RequestMethod.GET)
	public String addChangeRequest(HttpServletRequest request) {

        String caption = request.getParameter("crCaption");
		String author = request.getParameter("crAuthor");
		String parentId = request.getParameter("crParentId");
		String description = request.getParameter("crDescription");
		String priority = request.getParameter("crPriority");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy"); 
		Date dateOfFinish;
		Date dateOfStart;
		String hours=request.getParameter("crHours");
		List <String>watchers=new LinkedList<String>();
		for(String loginWatcher:request.getParameterValues("crWatchers"))
		watchers.add(_userDao.findByLogin(loginWatcher).getId());
		List <String>idTasks=new LinkedList<String>();		
		for(String idTask:request.getParameterValues("crIdTask"))
		idTasks.add(idTask);
		try
		{
		dateOfFinish=sdf.parse(request.getParameter("crDateOfFinish"));
		dateOfStart=sdf.parse(request.getParameter("crDateOfStart"));
		ChangeRequestEntity newCr = new ChangeRequestEntity( caption, author,parentId,description,priority,dateOfStart,dateOfFinish,hours,watchers,idTasks);
        _crDao.addChangeRequest(newCr);
		}
		catch (ParseException e) 
		{
        e.printStackTrace();
        }   
        return "redirect:/site/analist";
    }
		//Issue add
	@RequestMapping(value = "/issue/add", method = RequestMethod.GET)
	public String addIssue(HttpServletRequest request) {

		String caption=request.getParameter("issueCaption");
		String reporter=request.getParameter("issueReporter");
		
		ArrayList<String>assignees=new ArrayList<String>();
		for(String assignee:request.getParameterValues("issueAssignees"))
		assignees.add(_userDao.findByLogin(assignee).getId());
		
		ArrayList<String> watchers=new ArrayList<String>();
		for(String watcher:request.getParameterValues("issueWatchers"))
		watchers.add(_userDao.findByLogin(watcher).getId());
		
		ArrayList<String> subtasks=new ArrayList<String>();
		for(String subtask:request.getParameterValues("issueSubtasks"))
		subtasks.add(subtask);
		
		ArrayList<String> components=new ArrayList<String>();
		for(String component:request.getParameterValues("issueComponents"))
		components.add(component);
		
		String typeIssue=request.getParameter("issueType");
		String statusIssue=request.getParameter("issueStatus");
		String priorityIssue=request.getParameter("issuePriority");	
		Date creationDate;
		Date modificationDate;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy"); 
		
		String environment=request.getParameter("issueEnvironment");
		String description=request.getParameter("issueDescription");
		
		try
		{
		creationDate=sdf.parse(request.getParameter("issueCreationDate"));
		modificationDate=sdf.parse(request.getParameter("issueModificationDate"));
		IssueEntity newIssue = new IssueEntity(caption,reporter,
											assignees,watchers,
											subtasks,components,
											typeIssue,statusIssue,
											priorityIssue,creationDate,modificationDate,
											environment,description);
        _issueDao.addIssue(newIssue);
		}
		catch (ParseException e) 
		{
        e.printStackTrace();
        }
        return "redirect:/site/analist";
    }
	
}