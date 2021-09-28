package string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BOJ10930 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SHA256 sha256 = new SHA256();
		
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		
		String cryptogram = sha256.encrypt(s);
		
		System.out.println(cryptogram);
		
	}
}

class SHA256{
	
	public String encrypt(String text) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());
		
		return bytesToHex(md.digest());
	}
	
	
	private String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes) sb.append(String.format("%02x", b));
		
		return sb.toString();
	}
}