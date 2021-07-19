package divideandconquer;

import java.io.*;
import java.util.*;

public class BOJ11729 {
	
	static int cnt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		hanoi(n, 1, 3, 2);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	
	public static void hanoi(int n, int start, int to, int via) {
		if(n == 1) {
			sb.append(start + " " + to + "\n");
			cnt++;
			return;
		}
		
		hanoi(n-1, start, via, to);
		sb.append(start + " " + to + "\n");
		cnt++;
		hanoi(n-1, via, to, start);
	}
}
