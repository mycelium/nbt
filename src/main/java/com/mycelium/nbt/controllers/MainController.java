package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import com.mycelium.nbt.model.dao.ChangeRequestDao;
import com.mycelium.nbt.model.dao.IssueDao;
import com.mycelium.nbt.model.dao.UserDao;
import com.mycelium.nbt.model.dao.TaskDao;
import com.mycelium.nbt.model.dao.ModuleDao;
import com.mycelium.nbt.model.entities.ChangeRequestEntity;
import com.mycelium.nbt.model.entities.IssueEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.util.Properties;

@Controller
@RequestMapping(value = "/main")

public class MainController {
	Logger _logger = Logger.getLogger(MainController.class);

	@Autowired
	ChangeRequestDao _crDao;
	@Autowired
	IssueDao _issueDao;
	@Autowired
	UserDao _userDao;
	@Autowired
	TaskDao _taskDao;
	@Autowired
	ModuleDao _moduleDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getMainPage() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("crs", _crDao.findAll());
		mav.addObject("issues", _issueDao.findAll());
		mav.addObject("tasks", _taskDao.findAll());
		mav.addObject("modules", _moduleDao.findAll());
		return mav;
	}
	
	@RequestMapping(value="/newIssue", method = RequestMethod.GET)
	public ModelAndView createNewIssue() {
		ModelAndView mav = new ModelAndView("newIssue");
		mav.addObject("users", _userDao.findAll());
		mav.addObject("crs", _crDao.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/newIssue/add", method = RequestMethod.POST)
	public String addIssue(HttpServletRequest request,
			@RequestParam CommonsMultipartFile[] file) throws Exception {
		if (!request.getParameter("issueCaption").isEmpty()) {
			String caption = request.getParameter("issueCaption");
			String reporter = request.getParameter("issueReporter");
			List<String> assignees = new ArrayList<String>();
			if (request.getParameterValues("issueAssignees") != null)
				assignees = Arrays.asList(request
						.getParameterValues("issueAssignees"));
			List<String> watchers = new ArrayList<String>();
			if (request.getParameterValues("issueWatchers") != null)
				watchers = Arrays.asList(request
						.getParameterValues("issueWatchers"));
			String typeIssue = request.getParameter("issueType");
			String statusIssue = request.getParameter("issueStatus");
			String priorityIssue = request.getParameter("issuePriority");
			Date creationDate;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			if (request.getParameter("issueCreationDate") != null
					&& !request.getParameter("issueCreationDate").equals(""))
				creationDate = sdf.parse(request
						.getParameter("issueCreationDate"));
			else
				creationDate = new Date();

			String environment = request.getParameter("issueEnvironment");
			String description = request.getParameter("issueDescription");
			IssueEntity newIssue;
			_logger.warn("File length" + file.length);
			if (file != null && file.length > 0) {

				newIssue = new IssueEntity(caption, reporter, assignees,
						watchers, typeIssue, statusIssue, priorityIssue,
						creationDate, environment, description,
						file[0].getOriginalFilename());

				for (CommonsMultipartFile aFile : file) {

					System.out.println("Saving file: "
							+ aFile.getOriginalFilename());

					if (!aFile.getOriginalFilename().equals("")) {
						Properties prop = new Properties();
						try {
							prop.load(new FileInputStream("config.properties"));
							aFile.transferTo(new File(prop
									.getProperty("pathToIssueFiles")
									+ aFile.getOriginalFilename()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else
				newIssue = new IssueEntity(caption, reporter, assignees,
						watchers, typeIssue, statusIssue, priorityIssue,
						creationDate, environment, description, "");
			_issueDao.addIssue(newIssue);
		}
		return "redirect:/site/main";
	}
	}