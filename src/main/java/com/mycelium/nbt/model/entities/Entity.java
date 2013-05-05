package com.mycelium.nbt.model.entities;

import com.mycelium.nbt.utils.Util;

public abstract class Entity {
	public abstract String getId();
	public abstract void setId(String id);
	@Override
	public String toString(){
		return Util.toJSON(this);
	}
}
