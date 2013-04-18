package com.mycelium.nbt.model.enums;

public enum RoleType {
	Admin, Manager, Developer, Qa, ROLE_USER;

	private String _id;
	private String _caption;

	public RoleType setId(String id) {
		_id = id;
		return this;
	}

	public String getId() {
		return _id;
	}

	public String getCaption() {
		return _caption;
	}

	public RoleType setCaption(String caption) {
		_caption = caption;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{\"_id\":\"");
		sb.append(this.getId());
		sb.append("\",\"_caption\":\"");
		sb.append(this.getCaption());
		sb.append("\"}");
		return sb.toString();
	};

	public static RoleType getById(String id) {
		for (RoleType role : RoleType.values()) {
			if (role.getId().equals(id)) {
				return role;
			}
		}
		return null;
	}
}
