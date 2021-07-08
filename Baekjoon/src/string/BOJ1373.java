package string;

import java.util.Scanner;

public class BOJ1373 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		int n = s.length();
		s = (n % 3 == 1) ? "00" + s : (n % 3 == 2) ? "0" + s : s;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i+=3) {
			int num = (s.charAt(i) - '0') * 4 + (s.charAt(i+1) - '0') * 2+ (s.charAt(i+2) - '0') * 1;
			sb.append(num);
		}
		
		System.out.println(sb.toString());
	}
}
