package com.mycelium.nbt.model.entities;

import java.util.LinkedList;
import java.util.List;

public class ChangeRequestEntity extends Entity {

	private String _id;
	private String _caption;
	private String _authorId;
	private String _parentId;
	private List<String> _watcherIdList;
	private List<String> _taskIdList;

	public ChangeRequestEntity() {
		_caption = "";
		_authorId = "";
		_parentId = "";
		_watcherIdList = new LinkedList<String>();
		_taskIdList = new LinkedList<String>();

	}

	public ChangeRequestEntity(String caption, String author,
			List<String> watchers, List<String> idTasks, String parentId) {
		_caption = caption;
		_authorId = author;
		_parentId = parentId;
		_watcherIdList = watchers;
		_taskIdList = idTasks;
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
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

	public List<String> getIdTasks() {
		return _taskIdList;
	}

	public void setIdTasks(List<String> idTasks) {
		_taskIdList = idTasks;
	}

	public String getParentId() {
		return _parentId;
	}

	public void setParentId(String parentId) {
		_parentId = parentId;
	}
}
