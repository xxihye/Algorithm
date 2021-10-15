package math;

import java.util.*;

public class BOJ2163 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//행을 나누기 위해서 n-1, 나눠진 행 수 만큼 열을 나눔 
		// (n-1) + (n * (M-1)) -1 + n*m
		//행 == 1 : m-1
		//열 == 1 : n-1
		//둘다 == 1 : 1;
		int N = sc.nextInt(), M = sc.nextInt();
		
		if(N == 1 && M == 1) System.out.println(1);
		else if(N == 1) System.out.println(M - 1);
		else if(M == 1) System.out.println(N - 1);
		else System.out.println( N * M - 1);
	}
}
