package com.mycelium.nbt.model.entities;

import java.util.ArrayList;

public class CrEntity implements Entity {
	private String _id;
	private String _caption;
	private String _author;
	private ArrayList<String> _wathers;
	private ArrayList<String> _idTasks;
	private String _parentId;
	
	public CrEntity(){
	}
	public CrEntity(String caption){
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
	
	public String get_author() {
		return _author;
	}
	
	public void set_author(String _author) {
		this._author = _author;
	}
	
	public ArrayList<String> get_wathers() {
		return _wathers;
	}
	
	public void set_wathers(ArrayList<String> _wathers) {
		this._wathers = _wathers;
	}
	
	public ArrayList<String> get_idTasks() {
		return _idTasks;
	}
	
	public void set_idTasks(ArrayList<String> _idTasks) {
		this._idTasks = _idTasks;
	}
	
	public String get_parentId() {
		return _parentId;
	}
	
	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
	
	

}
