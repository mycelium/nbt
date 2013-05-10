package com.mycelium.nbt.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.mycelium.nbt.model.dao.ModuleDao;
import com.mycelium.nbt.model.dao.TaskDao;
import com.mycelium.nbt.model.entities.ModuleEntity;

@Controller
@RequestMapping(value = "/developer")
public class DeveloperController {
	Logger _logger = Logger.getLogger(DeveloperController.class);

	@Autowired
	ModuleDao _moduleDao;
	@Autowired
	TaskDao _taskDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getDeveloperPage() {
		ModelAndView mav = new ModelAndView("developer");
		mav.addObject("modules", _moduleDao.findAll());
		mav.addObject("tasks", _taskDao.findAll());
		return mav;
	}

	@RequestMapping(value="/newModule",method = RequestMethod.GET)
	public ModelAndView getNewModulePage() {
		ModelAndView mav = new ModelAndView("newModule");
		mav.addObject("tasks", _taskDao.findAll());	
		return mav;
	}

	@RequestMapping(value = "/editModule", method = RequestMethod.POST)
	public String updateModule(HttpServletRequest request) throws Exception {
		String id = request.getParameter("moduleId");
		ModuleEntity updatedModule = _moduleDao.findOne(id);
		updatedModule.setCaption(request.getParameter("moduleCaption"));
		updatedModule.setDescription(request.getParameter("moduleDescription"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		updatedModule.setDateOfCreation(sdf.parse(request
				.getParameter("moduleDateOfStart")));
		if (request.getParameter("moduleDateOfStart") != null
				&& !request.getParameter("moduleDateOfStart").equals(""))
			updatedModule.setDateOfCreation(sdf.parse(request
					.getParameter("moduleDateOfStart")));
		else
			updatedModule.setDateOfCreation(new Date());
		List<String> attachedTasks = new ArrayList<String>();

		if (request.getParameterValues("taskId") != null) {
			attachedTasks = new ArrayList<String>(Arrays.asList(request
					.getParameterValues("taskId")));
			updatedModule.setAttachedTasks(attachedTasks);
		}
		_moduleDao.addModule(updatedModule);

		return "redirect:/site/developer";
	}
	
	@RequestMapping(value="/newModule/add",method = RequestMethod.POST)
	public String addModule(HttpServletRequest request) throws Exception {
		
		String caption=request.getParameter("moduleCaption");
		String description=request.getParameter("moduleDescription");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy"); 
		Date dateOfCreation;
		if (request.getParameter("moduleDateOfStart") != null
				&& !request.getParameter("moduleDateOfStart").equals(""))
			dateOfCreation = sdf.parse(request.getParameter("moduleDateOfStart"));
		else
			dateOfCreation = new Date();
		List<String> attachedTasks=new ArrayList<String>();
		
		if ( request.getParameterValues("taskId")!=null )
			{
				attachedTasks=new ArrayList<String>(Arrays.asList(request.getParameterValues("taskId")));
			}
		ModuleEntity newModule =new ModuleEntity(caption,description,attachedTasks,dateOfCreation);
		_moduleDao.addModule(newModule);
		
		return "redirect:/site/developer";
		}
	
	
	@RequestMapping(value = "/module/{id}", method = RequestMethod.GET)
	public ModelAndView getModulesView(@PathVariable("id") String moduleId) {
		ModelAndView mav = new ModelAndView("module");
		mav.addObject("moduleView", _moduleDao.findOne(moduleId));
		mav.addObject("tasks", _taskDao.findAll());
		return mav;
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public ModelAndView getTaskView(@PathVariable("id") String taskId) {
		ModelAndView mav = new ModelAndView("taskView");
		mav.addObject("taskView", _taskDao.findOne(taskId));
		return mav;
	}

	@RequestMapping(value = "/addModuleToTask", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public void addModuleToTask(@RequestBody ModulesAndTasks mat) {

		String[] idOfModules = mat.getIdModuleList();
		String[] idOfTasks = mat.getIdTaskList();
		
		for (String idOfTask : idOfTasks) {
		
			for (String idOfModule : idOfModules) {
			
				if (!_taskDao.findOne(idOfTask).getAttachedModules()
						.contains(idOfModule))
					_taskDao.addModuleToTask(idOfTask, idOfModule);
				if (!_moduleDao.findOne(idOfModule).getAttachedTasks()
						.contains(idOfTask))
					_moduleDao.addTaskToModule(idOfModule, idOfTask);

			}
		}
	}

	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	public @ResponseBody
	InfoTask getInfo(@RequestBody ModulesAndTasks mat) {
		String[] idOfTasks = mat.getIdTaskList();

		InfoTask infoTask = new InfoTask();
		infoTask.setIdTask(_taskDao.findOne(idOfTasks[0]).getId());
		infoTask.setCaptionTask(_taskDao.findOne(idOfTasks[0]).getCaption());
		infoTask.setDescriptionTask(_taskDao.findOne(idOfTasks[0])
				.getDescription());
		HashMap<String, String> modulesIdAndCaption = new HashMap<String, String>();
		for (String moduleId : _taskDao.findOne(idOfTasks[0])
				.getAttachedModules())
			modulesIdAndCaption.put(moduleId, _moduleDao.findOne(moduleId)
					.getCaption());
		infoTask.setModulesIdAndCaption(modulesIdAndCaption);
		return infoTask;
	}

	@RequestMapping(value = "/delModuleFromTask", method = RequestMethod.GET)
	public String delModuleFromTask(HttpServletRequest request) {
		String idOfTask = request.getParameter("taskId");
		if (request.getParameterValues("assModules") != null) {
			_moduleDao.deleteModuleFromTask(idOfTask,
					request.getParameterValues("assModules"));
			for (String idOfModule : request.getParameterValues("assModules"))
				_taskDao.deleteModuleFromTask(idOfTask, idOfModule);
		}
		return "redirect:/site/manager";
	}
}