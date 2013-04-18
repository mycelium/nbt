package com.mycelium.nbt.model.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class ChangeRequestEntity extends Entity {

	private String _id;
	private String _caption;
	private String _authorId;
	private String _parentId;
	private String _description;
	private String _priority;
	private Date _dateOfStart;
	private Date _dateOfFinish;
	private String _hours;
	private List<String> _watcherIdList;
	private List<String> _taskIdList;
	private String _pathToFile;
	private List<String> _issueIdList;

	public ChangeRequestEntity() {
		_caption = "";
		_authorId = "";
		_parentId = "";
		_watcherIdList = new LinkedList<String>();
		_taskIdList = new LinkedList<String>();
		_issueIdList = new LinkedList<String>();

	}

	public ChangeRequestEntity(String caption, String author,
			 String parentId, String description,
			String priority,Date dateOfStart, Date dateOfFinish, String hours,
			List<String> watchers, List<String> issueIdList, String pathToFile) {
		_caption = caption;
		_authorId = author;
		_parentId = parentId;
		_watcherIdList = watchers;
		_issueIdList = issueIdList;
		_description=description;
		_priority=priority;
		_dateOfStart=dateOfStart;
		_dateOfFinish=dateOfFinish;
		_hours=hours;
		_pathToFile=pathToFile;
	}

	@Override
	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getCaption() {
		return _caption;
	}

	public void setCaption(String caption) {
		_caption = caption;
	}

	public String getAuthor() {
		return _authorId;
	}

	public void setAuthor(String author) {
		_authorId = author;
	}

	public List<String> getWatchers() {
		return _watcherIdList;
	}

	public void setWatchers(List<String> watchers) {
		_watcherIdList = watchers;
	}

	public List<String> getTaskIdList() {
		return _taskIdList;
	}

	public void setTaskIdList(List<String> idTasks) {
		_taskIdList = idTasks;
	}
	public List<String> getIssueIdList() {
		return _issueIdList;
	}

	public void setIssueIdList(List<String> issueIdList) {
		_issueIdList = issueIdList;
	}

	public String getParentId() {
		return _parentId;
	}

	public void setParentId(String parentId) {
		_parentId = parentId;
	}
	////////////////////////////////////////////////////
			public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description=description;
	}
	
	public String getPriority() {
		return _priority;
	}


	public void setPriority(String priority) {
		_priority=priority;
	}
		
	public Date getDateOfFinish() {
		return _dateOfFinish;
	}
		public void setDateOfFinish(Date dateOfFinish) {
		_dateOfFinish=dateOfFinish;
	}
	
	public Date getDateOfStart() {
		return _dateOfStart;
	}
		public void setDateOfStart(Date dateOfStart) {
		_dateOfStart=dateOfStart;
	}
	
	public String getHours() {
		return _hours;
	}


	public void setHours(String hours) {
		_hours=hours;
	}
	
	public void setPathToFile(String pathToFile)
	{
		_pathToFile=pathToFile;
	}
	public String getPathToFile()
	{
		return _pathToFile;
	}
	
}
