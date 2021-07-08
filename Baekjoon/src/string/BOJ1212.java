package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		StringBuilder sb= new StringBuilder();
		
		for(int i=0; i<s.length(); i++) {
			int n = s.charAt(i) - '0';
			sb.append(n / 4);
			sb.append((n%4)/2);
			sb.append((n%4)%2);
		}
		
		if(s.charAt(0) - '0' < 2) sb.deleteCharAt(0);
		if(s.charAt(0) - '0' < 4) sb.deleteCharAt(0);
		
		System.out.println(sb.toString());
		
	}
}
