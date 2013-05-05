package com.mycelium.nbt.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.google.gson.Gson;

public class Util {
	
	private static final Gson GSON=new Gson();

	public static String getMD5(String res) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String md5 = passwordEncoder.encodePassword(res, null);
		return md5;
	}
	
	public static String toJSON(Object obj){
		return GSON.toJson(obj);
	}

}
