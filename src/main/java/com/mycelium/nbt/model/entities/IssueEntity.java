package com.mycelium.nbt.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class IssueEntity extends Entity {
	private String _id;
	private String _caption;
	private String _reporter;
	private ArrayList<String> _assignees;
	private ArrayList<String> _watchers;
	private ArrayList<String> _subtasks;
	private ArrayList<String> _components;
	private String _typeIssue;
	private String _statusIssue;
	private String _priorityIssue;
	private Date _creationDate;
	private Date _modificationDate;
	private String _environment;
	private String _description;

	public IssueEntity() {
		_caption = "";
		_reporter = "";
		_assignees = new ArrayList<String>();
		_watchers = new ArrayList<String>();
		_subtasks = new ArrayList<String>();
		_components = new ArrayList<String>();
		_typeIssue = "";
		_statusIssue = "";
		_priorityIssue = "";
		_creationDate = new Date();
		_modificationDate =new Date();
		_environment = "";
		_description = "";
	}

	public IssueEntity(String caption, String reporter,
			ArrayList<String> assignees, ArrayList<String> watchers,
			ArrayList<String> subtasks, ArrayList<String> components,
			String typeIssue, String statusIssue, String priorityIssue,
			Date creationDate, Date modificationDate, String environment,
			String description) {
		_caption = caption;
		_reporter = reporter;
		_assignees = assignees;
		_watchers = watchers;
		_subtasks = subtasks;
		_components = components;
		_typeIssue = typeIssue;
		_statusIssue = statusIssue;
		_priorityIssue = priorityIssue;
		_creationDate = creationDate;
		_modificationDate = modificationDate;
		_environment = environment;
		_description = description;
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

	public String getReporter() {
		return _reporter;
	}

	public void setReporter(String reporter) {
		_reporter = reporter;
	}

	public ArrayList<String> getAssignees() {
		return _assignees;
	}

	public void setAssignees(ArrayList<String> assignees) {
		_assignees = assignees;
	}

	public ArrayList<String> getWatchers() {
		return _watchers;
	}

	public void setWatchers(ArrayList<String> watchers) {
		_watchers = watchers;
	}

	public ArrayList<String> getSubtasks() {
		return _subtasks;
	}

	public void setSubtasks(ArrayList<String> subtasks) {
		_subtasks = subtasks;
	}

	public ArrayList<String> getComponents() {
		return _components;
	}

	public void setComponents(ArrayList<String> components) {
		_components = components;
	}

	public String getTypeIssue() {
		return _typeIssue;
	}

	public void setTypeIssue(String typeIssue) {
		this._typeIssue = typeIssue;
	}

	public String getStatusIssue() {
		return _statusIssue;
	}

	public void setStatusIssue(String statusIssue) {
		this._statusIssue = statusIssue;
	}

	public String getPriorityIssue() {
		return _priorityIssue;
	}

	public void setPriorityIssue(String priorityIssue) {
		_priorityIssue = priorityIssue;
	}

	public Date getCreationDate() {
		return _creationDate;
	}

	public void setCreationDate(Date creationDate) {
		_creationDate = creationDate;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	public String getEnvironment() {
		return _environment;
	}

	public void setEnvironment(String environment) {
		_environment = environment;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

}
