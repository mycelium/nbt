package com.mycelium.nbt.model.enums;

public enum RoleEnum {
	ADMIN,
	USER;
	
	public String toString() {
        return name().toLowerCase();
	};
}
