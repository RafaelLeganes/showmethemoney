package com.sinensia.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class conversorSHA256 {

	public String convertirSHA256(String password) throws Exception{
		MessageDigest md = null;
		StringBuffer sb;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte [] hash = md.digest(password.getBytes());
			sb = new StringBuffer();
			for(byte b: hash) {
				sb.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}
}
