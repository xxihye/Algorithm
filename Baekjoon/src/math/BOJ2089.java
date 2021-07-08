package math;

import java.util.Scanner;

public class BOJ2089 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		if(n == 0) {
			System.out.println(0);
			return;
		}

		StringBuilder sb = new StringBuilder();
		
		while(n != 0) {
			sb.append(Math.abs(n % -2));
			n = (int) Math.ceil((n/-2.0));
		}
		System.out.println(sb.reverse().toString());
	}

}
