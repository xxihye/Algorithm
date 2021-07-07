package math;

import java.util.Scanner;

public class BOJ1850 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long a = sc.nextLong(), b = sc.nextLong();
		
		long gcd = gcd(Math.max(a, b), Math.min(a, b));
		
		StringBuilder sb = new StringBuilder();
		while(gcd-- > 0) sb.append("1");
		
		System.out.println(sb.toString());
		
	} 
	
	public static long gcd(long p, long q) {
		if(q == 0) return p;
		return gcd(q, p%q);
	}
	
}
