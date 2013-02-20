package com.mycelium.nbt.model.entities;

public class RoleEntity extends Entity {
	private String _id;
	private String _caption;

	public RoleEntity() {
	}

	public RoleEntity(String caption) {
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

	public String getCaption() {
		return _caption;
	}

	public RoleEntity setCaption(String caption) {
		_caption = caption;
		return this;
	}

}
