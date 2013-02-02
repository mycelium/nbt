package com.mycelium.nbt.model.entities;

import com.mycelium.nbt.utils.Util;

public class UserEntity implements Entity {
	private String _id;
	private String _login;
	private String _email;
	private String _firstName;
	private String _lastName;
	private String _password;
	
	public UserEntity(){
	}
	public UserEntity(String login, String email, String firstName, String lastName, String password){
		_login=login;
		_email=email;
		_firstName=firstName;
		_lastName=lastName;
		_password=Util.getMD5(password);
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public void setId(String id) {
		_id = id;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

}