package com.mycelium.nbt.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Util {

	public static String getMD5(String res) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String md5 = passwordEncoder.encodePassword(res, null);
		return md5;
	}

}
