package math;

import java.util.Scanner;

public class BOJ2745 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String n = sc.next();
		int b = sc.nextInt();
		
		long num = 0;
		
		int length = n.length();
		for(int i=length-1; i>=0; i--) {
			if(n.charAt(i) - '0' < 10) num += (n.charAt(i) - '0') * Math.pow(b, length-i-1); 
			else num += (n.charAt(i) - 'A' + 10) * Math.pow(b, length-i-1);
		}
		
		System.out.println(num);
	}
}
