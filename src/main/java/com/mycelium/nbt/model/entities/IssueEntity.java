package com.mycelium.nbt.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class IssueEntity implements Entity {
	private String _id;
	private String _caption;
	private String _reporter;
	private ArrayList<String> _assignees;
	private ArrayList<String> _watchers;
	private ArrayList<Integer> _subtasks;
	private ArrayList<String> _components;
	private String _typeIssue;
	private String _statusIssue;
	private String _priorityIssue;
	private Date _creationDate;
	private Date _modificationDate;
	private String _environment;
	private String _description;
	
	public IssueEntity(){
	}
	public IssueEntity(String caption){
		_caption = caption;
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public void setId(String id) {
		this._id = id;
	}
	
	public String get_caption() {
		return _caption;
	}
	public void set_caption(String _caption) {
		this._caption = _caption;
	}
	
	public String get_reporter() {
		return _reporter;
	}
	
	public void set_reporter(String _reporter) {
		this._reporter = _reporter;
	}
	
	public ArrayList<String> get_assignees() {
		return _assignees;
	}
	
	public void set_assignees(ArrayList<String> _assignees) {
		this._assignees = _assignees;
	}
	
	public ArrayList<String> get_watchers() {
		return _watchers;
	}
	
	public void set_watchers(ArrayList<String> _watchers) {
		this._watchers = _watchers;
	}
	
	public ArrayList<Integer> get_subtasks() {
		return _subtasks;
	}
	
	public void set_subtasks(ArrayList<Integer> _subtasks) {
		this._subtasks = _subtasks;
	}
	
	public ArrayList<String> get_components() {
		return _components;
	}
	
	public void set_components(ArrayList<String> _components) {
		this._components = _components;
	}
	
	public String get_typeIssue() {
		return _typeIssue;
	}
	
	public void set_typeIssue(String _typeIssue) {
		this._typeIssue = _typeIssue;
	}
	
	public String get_statusIssue() {
		return _statusIssue;
	}
	
	public void set_statusIssue(String _statusIssue) {
		this._statusIssue = _statusIssue;
	}
	
	public String get_priorityIssue() {
		return _priorityIssue;
	}
	
	public void set_priorityIssue(String _priorityIssue) {
		this._priorityIssue = _priorityIssue;
	}
	
	public Date get_creationDate() {
		return _creationDate;
	}
	
	public void set_creationDate(Date _creationDate) {
		this._creationDate = _creationDate;
	}
	
	public Date get_modificationDate() {
		return _modificationDate;
	}
	
	public void set_modificationDate(Date _modificationDate) {
		this._modificationDate = _modificationDate;
	}
	public String get_environment() {
		return _environment;
	}
	
	public void set_environment(String _environment) {
		this._environment = _environment;
	}
	
	public String get_description() {
		return _description;
	}
	
	public void set_description(String _description) {
		this._description = _description;
	}
	
	

}
