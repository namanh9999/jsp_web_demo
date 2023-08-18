package util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encryption {
	// use sha-1
	public static Encryption getInstance() {
		return new Encryption();
	}
	public  String toSHA1(String pass) {
		String salt = "02393ksdd@@@qipwoeuasdjwer93jasd92";
		String result = null;
		pass = pass + salt;

		try {
			byte[] dataBytes = pass.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
