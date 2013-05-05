package com.mycelium.nbt.model.entities;

public class StatusEntity extends Entity {
	private String _id;
	private String _caption;
	
	public StatusEntity(){
	}
	public StatusEntity(String caption){
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
