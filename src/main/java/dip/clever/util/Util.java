package dip.clever.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Util {
	public static String encode(String password) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
		}		
		return hashcode(messageDigest.digest());		
	}
	
	private static String hashcode(byte[] password) {
		StringBuilder stringBuilder = new StringBuilder();
		for(byte b : password) {
			stringBuilder.append(String.format("%02x", b)); // b에 데이터값을 16진수 형으로 표기함  
		}
		return stringBuilder.toString();
	}
}