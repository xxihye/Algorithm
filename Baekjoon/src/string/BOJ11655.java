package string;

import java.util.Scanner;

public class BOJ11655 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			
			if(c >= 'a') {
				sb.append((char)('a' + (c - 'a' + 13) % 26));
			}else if(c >= 'A') {
				sb.append((char)('A' + (c - 'A' + 13) % 26));
			}else {
				sb.append(c);
			}
		}
		
		System.out.println(sb.toString());

	}

}
