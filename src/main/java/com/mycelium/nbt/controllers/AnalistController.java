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
import java.util.HashMap;
import com.mycelium.nbt.controllers.MarkIssues;
import com.mycelium.nbt.controllers.IssuesAndCRs;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.SimpleFormController;
import java.io.IOException;
import java.io.File;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


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
		mav.addObject("issues", sortIssues(_issueDao.findAll()));		
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
		mav.addObject("issues",_issueDao.findAll());		
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
		mav.addObject("issues",_issueDao.findAll());			
		return mav;
	}
	
	
	//CR
	@RequestMapping(value = "/cr/add", method = RequestMethod.POST)
	public String addChangeRequest(HttpServletRequest request,
								@RequestParam CommonsMultipartFile[] file) throws Exception  
	{
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
		if(request.getParameterValues("crIdTask")!=null)
			for(String idTask:request.getParameterValues("crIdTask"))
				idTasks.add(idTask);
				
		if (file != null && file.length > 0) 
			{
				
					dateOfFinish=sdf.parse(request.getParameter("crDateOfFinish"));
					dateOfStart=sdf.parse(request.getParameter("crDateOfStart"));
					ChangeRequestEntity newCr = new ChangeRequestEntity( caption, author,parentId,description,priority,dateOfStart,dateOfFinish,hours,watchers,idTasks,"img/cr/"+ file[0].getOriginalFilename());
					_crDao.addChangeRequest(newCr);
					for (CommonsMultipartFile aFile : file){
					 
					System.out.println("Saving file: " + aFile.getOriginalFilename());
					 
					if (!aFile.getOriginalFilename().equals("")) {
						aFile.transferTo(new File(request.getRealPath("/img/cr/")+ aFile.getOriginalFilename()));
					}
				} 			
			}
        return "redirect:/site/analist";
    }
		//Issue add
	@RequestMapping(value = "/issue/add", method = RequestMethod.POST)
	public String addIssue(HttpServletRequest request,
								@RequestParam CommonsMultipartFile[] file) throws Exception
	{
		String caption=request.getParameter("issueCaption");
		String reporter=request.getParameter("issueReporter");
		
		ArrayList<String>assignees=new ArrayList<String>(Arrays.asList(request.getParameterValues("issueAssignees")));
		ArrayList<String> watchers=new ArrayList<String>(Arrays.asList(request.getParameterValues("issueWatchers")));
		ArrayList<String> subtasks=new ArrayList<String>(Arrays.asList(request.getParameterValues("issueSubtasks")));
		ArrayList<String> components=new ArrayList<String>(Arrays.asList(request.getParameterValues("issueComponents")));
		String typeIssue=request.getParameter("issueType");
		String statusIssue=request.getParameter("issueStatus");
		String priorityIssue=request.getParameter("issuePriority");	
		Date creationDate;
		Date modificationDate;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy"); 
		
		String environment=request.getParameter("issueEnvironment");
		String description=request.getParameter("issueDescription");
		
		if (file != null && file.length > 0) 
		{
		creationDate=sdf.parse(request.getParameter("issueCreationDate"));
		modificationDate=sdf.parse(request.getParameter("issueModificationDate"));
		IssueEntity newIssue = new IssueEntity(caption,reporter,
											assignees,watchers,
											subtasks,components,
											typeIssue,statusIssue,
											priorityIssue,creationDate,modificationDate,
											environment,description,"img/issue/"+ file[0].getOriginalFilename());
        _issueDao.addIssue(newIssue);
		for (CommonsMultipartFile aFile : file){
					 
					System.out.println("Saving file: " + aFile.getOriginalFilename());
					 
					if (!aFile.getOriginalFilename().equals("")) {
						aFile.transferTo(new File(request.getRealPath("/img/issue/")+ aFile.getOriginalFilename()));
					}
				}
		}
        return "redirect:/site/analist";
    }
	
	
@RequestMapping(value = "/mark", method = RequestMethod.POST, headers = {"content-type=application/json"})  
//@ResponseStatus(HttpStatus.OK)	
    public String markIssue(@RequestBody MarkIssues markIssues) {

		String[] idOfIssues=markIssues.getIdList();
		String mymarker=markIssues.getMarker();
		_logger.warn(mymarker);
		for(String idOfIssue:idOfIssues)
			{
				_issueDao.updateMarker(_issueDao.findOne(idOfIssue).getId(),mymarker);
			}
		return "redirect:/site/analist";
    }
	
	@RequestMapping(value = "/addIssueToCr", method = RequestMethod.POST, headers = {"content-type=application/json"})
	public String addIssuesToCR(@RequestBody IssuesAndCRs iac) {

	String[] idOfCRs=iac.getIdCRList();
	String[] idOfIssues=iac.getIdIssueList();
	for(String idOfCR:idOfCRs)
	{
		_logger.warn("crid="+idOfCR);
		for(String id:idOfIssues)
		{
			if(!_crDao.findOne(idOfCR).getTaskIdList().contains(id))
				_crDao.addIssue(idOfCR,id);  
			if(!_issueDao.findOne(id).getAssignees().contains(idOfCR))
				_issueDao.addCR(id,idOfCR);
			_logger.warn("issueid="+id);
		}				
	}
        return "redirect:/site/analist";
    }
	
	@RequestMapping(value = "/delIssueFromCr", method = RequestMethod.GET)
			public String delIssueFromCR(HttpServletRequest request) {
			String idOfCR=request.getParameter("crId");	
			if(request.getParameterValues("assIssues")!=null)
			{
			_logger.warn("lala");
				_crDao.delIssue(idOfCR,request.getParameterValues("assIssues"));
				for(String idOfIssue:request.getParameterValues("assIssues") )
				_issueDao.delCR(idOfIssue,idOfCR);
			} 		
				return "redirect:/site/analist";
			}
	
	
	
	
	
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	public @ResponseBody String getInfo(@RequestBody IssuesAndCRs iac) {
	String[] CRsId=iac.getIdCRList();
	String result="";
	if(CRsId!=null)
	{
			for(String CRsIssue:_crDao.findOne(CRsId[0]).getIssueIdList())
			result+="ID: "+CRsIssue+";\nCaption: "+_issueDao.findOne(CRsIssue).getCaption()+"\n";
		
		return "CR caption: "+_crDao.findOne(CRsId[0]).getCaption()+"\nCR description : "+_crDao.findOne(CRsId[0]).getDescription()+"\nAssigned Issues: \n"+result;
	}
	else return "In";
		
    }
	
	public ArrayList<IssueEntity> sortIssues(List<IssueEntity> issues)
	{
		ArrayList<IssueEntity> sortIssues=new ArrayList(issues);
		ArrayList<HashMap<Integer,IssueEntity>> tmpIssues=new ArrayList<HashMap<Integer,IssueEntity>>();
		for(int i=0;i<sortIssues.size();++i)
		{
			if(!sortIssues.get(i).getMarker().equals(""))
			{
				HashMap hashMap=new HashMap<Integer,IssueEntity>();
				hashMap.put(i,sortIssues.get(i));
				tmpIssues.add(hashMap);	
			}
			else if(!tmpIssues.isEmpty() && sortIssues.get(i).getMarker().equals(""))
			{
				sortIssues.set((Integer)tmpIssues.get(0).keySet().toArray()[0],sortIssues.get(i));
				sortIssues.set(i,tmpIssues.get(0).get(tmpIssues.get(0).keySet().toArray()[0]));
				tmpIssues.remove(0);
			}
		}
		return sortIssues;
	}

	
}