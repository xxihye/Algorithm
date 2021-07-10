package math;

import java.io.IOException;
import java.util.Scanner;

public class BOJ11576 {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(),
			b = sc.nextInt();
		
		int m = sc.nextInt();
		sc.close();
		
		int n = 0;
		for(int i= m-1; i>= 0; i--) {
			n += sc.nextInt() * Math.pow(a, i);
		}
		
		String s = "";
		while(n / b > 0) {
			s = " " + (n % b) + s;
			n /= b;
		}
		
		System.out.println(n + s);
		
	}
}
