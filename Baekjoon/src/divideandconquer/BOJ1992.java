package divideandconquer;

import java.io.*;
import java.util.*;

public class BOJ1992 {
	
	static StringBuilder sb = new StringBuilder();
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) arr[i][j] = s.charAt(j) - '0';
		}
		
		divideAndConquer(0, 0, n);
		
		System.out.println(sb.toString());
		
	}
	
	public static void divideAndConquer(int x, int y, int l) {
		if(isSame(x, y, l)) {
			sb.append(arr[x][y]);
		}else {
			int nl = l / 2;

			sb.append('(');
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					divideAndConquer(x + (i * nl), y + (j * nl), nl);
				}
			}
			sb.append(')');
		}
	}
	
	
	public static boolean isSame(int x, int y, int l) {
		int value = arr[x][y];
		
		for(int i=x; i<x+l; i++) {
			for(int j=y; j<y+l; j++) {
				if(value != arr[i][j]) return false;
			}
		}
		return true;
	}

}
