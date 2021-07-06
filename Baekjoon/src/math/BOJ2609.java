package math;

import java.util.Scanner;

public class BOJ2609 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(),
			b = sc.nextInt();
		
		sc.close();
		
		int gcd = gcd(Math.max(a, b), Math.min(a, b)); 
		int lcm = lcm(a, b, gcd);
		
		System.out.println(gcd);
		System.out.println(lcm);
	} 
	
	public static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd( b, a%b);
	}
	
	public static int lcm(int a, int b, int gcd) {
		return (a / gcd) * b; 
	}
	
}
