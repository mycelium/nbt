package com.mycelium.nbt.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModuleEntity extends Entity {

	private String _id;
	private String _caption;
	private String  _description;
	private List<String> _attachedTasks;
	private Date _dateOfCreation;

public ModuleEntity()
{
	_caption="";
	_description="";
	_attachedTasks=new ArrayList<String>();
	_dateOfCreation=new Date();	
}

public ModuleEntity(String caption,String  description,List<String> attachedTasks,Date dateOfCreation)
{
	
	_caption=caption;
	_description=description;
	_attachedTasks=attachedTasks;
	_dateOfCreation=dateOfCreation;	
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description=description;
	}
	
	public void setCaption(String caption) {
		_caption = caption;
	}
	
	public List<String> getAttachedTasks() {
		return _attachedTasks;
	}

	public void setAttachedTasks(List<String> attachedTasks) {
		_attachedTasks = attachedTasks;
	}
		
	public Date getDateOfCreation() {
		return _dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		_dateOfCreation = dateOfCreation;
	}

}