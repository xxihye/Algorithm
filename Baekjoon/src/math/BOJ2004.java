package math;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2004 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), r = sc.nextInt();
		sc.close();
		
		int cntFive = get(n, 5) - get(n-r, 5) - get(r, 5);
		int cntTwo = get(n, 2) - get(n-r, 2) - get(r, 2);
		
		System.out.println(Math.min(cntFive, cntTwo));
	}
	
	public static int get(int n, int d) {
		int cnt = 0;
		
		while(n >= d) {
			cnt += (n / d);
			n /= d;
		}
		
		return cnt;
	}
}
