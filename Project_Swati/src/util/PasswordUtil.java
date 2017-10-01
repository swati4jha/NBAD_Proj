package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;


public class PasswordUtil {
	public static String hashPassword(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length*2);
		for(byte b : mdArray){
			int v = b & 0xff;
			if(v<16){
				sb.append("0");
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
	
	public static String getSalt(){
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}
	public static HashMap<String, String> hashandSaltPassword(String password) throws NoSuchAlgorithmException{
		String salt = getSalt();
		HashMap<String, String> saltHash = new HashMap<String, String>();
		saltHash.put("salt", salt);
		saltHash.put("password", hashPassword(password + salt));
		return saltHash;
		
	}

}
