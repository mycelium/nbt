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
import com.mycelium.nbt.model.entities.ChangeRequestEntity;
import com.mycelium.nbt.model.entities.IssueEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.mycelium.nbt.model.dao.UserDao;
import com.mycelium.nbt.model.dao.TaskDao;
import java.util.Arrays;
import java.util.HashMap;
import com.mycelium.nbt.controllers.MarkIssues;
import com.mycelium.nbt.controllers.IssuesAndCRs;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.util.Properties;
import com.mycelium.nbt.controllers.InfoCR;

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
	@Autowired
	TaskDao _taskDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAnalistPage() {
		ModelAndView mav = new ModelAndView("analist");
		mav.addObject("crs", _crDao.findAll());
		mav.addObject("issues", sortIssues(_issueDao.findAll()));
		mav.addObject("countNewIssues",
				getCountNotLinkedIssues(_issueDao.findAll()));
		mav.addObject("countNotLinkedIssues",
				getCountNotLinkedIssues(_issueDao.findAll()));
		return mav;
	}

	@RequestMapping(value = "/issue/new", method = RequestMethod.GET)
	public ModelAndView createNewIssie() {
		ModelAndView mav = new ModelAndView("newIssue");
		mav.addObject("users", _userDao.findAll());
		return mav;
	}

	@RequestMapping(value = "/cr/new", method = RequestMethod.GET)
	public ModelAndView createNewCr() {
		ModelAndView mav = new ModelAndView("newCr");
		mav.addObject("users", _userDao.findAll());
		mav.addObject("issues", _issueDao.findAll());
		return mav;
	}

	@RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
	public ModelAndView getIssueView(@PathVariable("id") String issueViewId) {
		ModelAndView mav = new ModelAndView("issue");
		mav.addObject("issueView", _issueDao.findOne(issueViewId));
		return mav;
	}

	@RequestMapping(value = "/cr/{id}", method = RequestMethod.GET)
	public ModelAndView getCrView(@PathVariable("id") String changeRequestId) {
		ModelAndView mav = new ModelAndView("cr");
		mav.addObject("crView", _crDao.findOne(changeRequestId));
		mav.addObject("issues", _issueDao.findAll());
		return mav;
	}
	@RequestMapping(value = "/cr/edit", method = RequestMethod.POST)
	public String editChangeRequest( HttpServletRequest request)
	{
		ChangeRequestEntity updatedCR;
		String changeRequestId=request.getParameter("crId");
		if(_crDao.findOne(changeRequestId)!=null)
			 {
				updatedCR=_crDao.findOne(changeRequestId);
				updatedCR.setCaption(request.getParameter("crCaption"));
				updatedCR.setDescription(request.getParameter("crDescription"));
				_crDao.addChangeRequest(updatedCR);
			 }
		
		return "redirect:/site/analist";
	}
	// CR
	@RequestMapping(value = "/cr/add", method = RequestMethod.POST)
	public String addChangeRequest(
			HttpServletRequest request,
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] file)
			throws Exception {
		if (!request.getParameter("crCaption").isEmpty()) {
			String caption = request.getParameter("crCaption");
			String author = request.getParameter("crAuthor");
			String parentId = request.getParameter("crParentId");
			String description = request.getParameter("crDescription");
			String priority = request.getParameter("crPriority");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			String hours = request.getParameter("crHours");
			List<String> watchers = new ArrayList<String>();
			if (request.getParameterValues("crWatchers") != null)
				watchers = Arrays.asList(request
						.getParameterValues("crWatchers"));
			List<String> issuesIdList = new ArrayList<String>();
			if (request.getParameterValues("issuesId") != null)
				issuesIdList = Arrays.asList(request
						.getParameterValues("issuesId"));
			Date dateOfFinish;
			Date dateOfStart;
			if (request.getParameter("crDateOfFinish") != null
					&& !request.getParameter("crDateOfFinish").equals(""))
				dateOfFinish = sdf
						.parse(request.getParameter("crDateOfFinish"));
			else
				dateOfFinish = new Date();
			if (request.getParameter("crDateOfStart") != null
					&& !request.getParameter("crDateOfStart").equals(""))
				dateOfStart = sdf.parse(request.getParameter("crDateOfStart"));
			else
				dateOfStart = new Date();
			ChangeRequestEntity newCr;
			if (file != null && file.length > 0) {
				newCr = new ChangeRequestEntity(caption, author, parentId,
						description, priority, dateOfStart, dateOfFinish,
						hours, watchers, issuesIdList,
						file[0].getOriginalFilename());

				for (CommonsMultipartFile aFile : file) {

					_logger.info("Saving file: " + aFile.getOriginalFilename());

					if (!aFile.getOriginalFilename().equals("")) {
						// aFile.transferTo(new
						// File(request.getRealPath("/img/cr/")+
						// aFile.getOriginalFilename()));
						Properties prop = new Properties();
						try {
							prop.load(new FileInputStream("config.properties"));
							aFile.transferTo(new File(prop
									.getProperty("pathToCRFiles")
									+ aFile.getOriginalFilename()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else
				newCr = new ChangeRequestEntity(caption, author, parentId,
						description, priority, dateOfStart, dateOfFinish,
						hours, watchers, issuesIdList, "");
			_crDao.addChangeRequest(newCr);
		}
		return "redirect:/site/analist";
	}

	// Issue add
	@RequestMapping(value = "/issue/add", method = RequestMethod.POST)
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
			/*
			 * List<String> subtasks=new ArrayList<String>();
			 * if(request.getParameterValues("issueSubtasks")!=null)
			 * subtasks=Arrays
			 * .asList(request.getParameterValues("issueSubtasks"));
			 * List<String> components=new ArrayList<String>();
			 * if(request.getParameterValues("issueComponents")!=null)
			 * components=Arrays
			 * .asList(request.getParameterValues("issueComponents"));
			 */
			String typeIssue = request.getParameter("issueType");
			String statusIssue = request.getParameter("issueStatus");
			String priorityIssue = request.getParameter("issuePriority");
			Date creationDate;
			// Date modificationDate;
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
						/*
						 * aFile.transferTo(new File(request
						 * .getRealPath("/img/issue/") +
						 * aFile.getOriginalFilename()));
						 */
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
		return "redirect:/site/analist";
	}

	@RequestMapping(value = "/mark", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String markIssue(@RequestBody MarkIssues markIssues) {

		String[] idOfIssues = markIssues.getIdList();
		String mymarker = markIssues.getMarker();
		for (String idOfIssue : idOfIssues) {
			_issueDao.updateMarker(_issueDao.findOne(idOfIssue).getId(),
					mymarker);
		}
		return "redirect:/site/analist";
	}

	@RequestMapping(value = "/addIssueToCr", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public/* @ResponseBody InfoCR */void addIssuesToCR(
			@RequestBody IssuesAndCRs iac) {

		String[] idOfCRs = iac.getIdCRList();
		String[] idOfIssues = iac.getIdIssueList();
		if (idOfCRs != null && idOfIssues != null) {
			for (String idOfCR : idOfCRs) {
				for (String id : idOfIssues) {
					if (!_crDao.findOne(idOfCR).getIssueIdList().contains(id))
						_crDao.addIssue(idOfCR, id);
					if (!_issueDao.findOne(id).getAttachedCRs()
							.contains(idOfCR))
						_issueDao.addCR(id, idOfCR);
				}
			}
		}
	}

	@RequestMapping(value = "/delIssueFromCr", method = RequestMethod.POST)
	public void delIssueFromCR(/*HttpServletRequest request*/
			@RequestBody IssuesAndCRs iac) {
		/*String idOfCR = request.getParameter("crId");
		if (request.getParameterValues("assIssues") != null) {
			_crDao.delIssue(idOfCR, request.getParameterValues("assIssues"));
			for (String idOfIssue : request.getParameterValues("assIssues"))
				_issueDao.delCR(idOfIssue, idOfCR);
		}*/
		String[] idOfCR = iac.getIdCRList();
		String[] idOfIssues = iac.getIdIssueList();
		if ( idOfIssues!= null) {
			_crDao.delIssue(idOfCR[0], idOfIssues);
			for (String idOfIssue : idOfIssues)
				_issueDao.delCR(idOfIssue, idOfCR[0]);
		}
		
	}
	@RequestMapping(value = "/delFile", method = RequestMethod.POST)
	public void delFile(HttpServletRequest request)
	{
		String crId=request.getParameter("crId");
		String fileName=request.getParameter("delFile");
		String pathToFile;
		Properties prop=new Properties();
		try{
		prop.load(new FileInputStream("config.properties"));
		pathToFile=prop.getProperty("pathToCRFiles");
		File deleteFile=new File(pathToFile+fileName);
		if(deleteFile.exists())
			deleteFile.delete();
		_crDao.updateFile(crId,"");
		}
		catch(IOException e)
		{e.printStackTrace();}
	}

	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	public @ResponseBody
	InfoCR getInfo(@RequestBody IssuesAndCRs iac) {
		String[] idOfCRs = iac.getIdCRList();
		/*
		 * String result = ""; if (CRsId != null) { for (String CRsIssue :
		 * _crDao.findOne(CRsId[0]).getIssueIdList()) result += "ID:" + CRsIssue
		 * + ";\nCaption: " + _issueDao.findOne(CRsIssue).getCaption() + "\n";
		 * 
		 * return "CR caption: " + _crDao.findOne(CRsId[0]).getCaption() +
		 * "\nCR description : " + _crDao.findOne(CRsId[0]).getDescription() +
		 * "\nAssigned Issues: \n" + result; } else return "In";
		 */
		InfoCR infoCR = new InfoCR();
		infoCR.setIdCR(_crDao.findOne(idOfCRs[0]).getId());
		infoCR.setCaptionCR(_crDao.findOne(idOfCRs[0]).getCaption());
		infoCR.setDescriptionCR(_crDao.findOne(idOfCRs[0]).getDescription());
		List<String> issuesCaption = new ArrayList<String>();
		for (String issueId : _crDao.findOne(idOfCRs[0]).getIssueIdList())
			issuesCaption.add(_issueDao.findOne(issueId).getCaption());
		infoCR.setAssignedIssuesCaption(issuesCaption);
		List<String> tasksCaption = new ArrayList<String>();
		for (String taskId : _crDao.findOne(idOfCRs[0]).getTaskIdList())
			tasksCaption.add(_taskDao.findOne(taskId).getCaption());
		infoCR.setAssignedIssuesCaption(issuesCaption);
		infoCR.setAssignedTasksCaption(tasksCaption);
		HashMap<String, String> issuesIdAndCaption = new HashMap<String, String>();
		for (String issueId : _crDao.findOne(idOfCRs[0]).getIssueIdList())
			issuesIdAndCaption.put(issueId, _issueDao.findOne(issueId)
					.getCaption());
		infoCR.setIssuesIdAndCaption(issuesIdAndCaption);
		return infoCR;

	}

	public ArrayList<IssueEntity> sortIssues(List<IssueEntity> issues) {
		ArrayList<IssueEntity> sortIssues = new ArrayList<IssueEntity>(issues);
		Collections.sort(sortIssues, new Comparator<IssueEntity>() {
			@Override
			public int compare(IssueEntity g1, IssueEntity g2) {
				return g1.getMarker().compareTo(g2.getMarker());
			}
		});
		/*
		 * ArrayList<HashMap<Integer, IssueEntity>> tmpIssues = new
		 * ArrayList<HashMap<Integer, IssueEntity>>(); for (int i = 0; i <
		 * sortIssues.size(); ++i) { if
		 * (!sortIssues.get(i).getMarker().equals("")) { HashMap<Integer,
		 * IssueEntity> hashMap = new HashMap<Integer, IssueEntity>();
		 * hashMap.put(i, sortIssues.get(i)); tmpIssues.add(hashMap); } else if
		 * (!tmpIssues.isEmpty() && sortIssues.get(i).getMarker().equals("")) {
		 * sortIssues.set( (Integer) tmpIssues.get(0).keySet().toArray()[0],
		 * sortIssues.get(i)); sortIssues.set( i, tmpIssues.get(0).get(
		 * tmpIssues.get(0).keySet().toArray()[0])); tmpIssues.remove(0); } }
		 */
		return sortIssues;
	}

	public int getCountNotLinkedIssues(List<IssueEntity> issues) {
		int count = 0;
		for (IssueEntity issue : issues) {
			if (issue.getAttachedCRs().isEmpty())
				++count;
		}
		return count;
	}

}