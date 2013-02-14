package com.mycelium.nbt.model.entities;

public class PriorityEntity implements Entity {
	private String _id;
	private String _caption;
	
	public PriorityEntity(){
	}
	public PriorityEntity(String caption){
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
	
	

}