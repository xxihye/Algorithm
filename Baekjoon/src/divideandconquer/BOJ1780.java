package divideandconquer;

import java.io.*;
import java.util.*;

public class BOJ1780 {
	
	static int[] cnt = new int[3];
	static int n;
	static int[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) arr[i][j] = Integer.parseInt(st.nextToken()) + 1;
		}
		
		
		divideAndConquer(0, 0, n);
		
		System.out.println(Arrays.toString(cnt));
		
	}
	
	
	public static void divideAndConquer(int x, int y, int l) {
		if(isSame(x, y, l)) cnt[arr[x][y]]++;
		else {
			int nl = l / 3;
			
			for(int i=0; i<3; i++) 
				for(int j=0; j<3; j++) 
					divideAndConquer(x + (nl * i), y + (nl * j), nl);
		}
		
	}
	
	
	public static boolean isSame(int x, int y, int l) {
		int tmp = arr[x][y];
		
		for(int i=x; i<x+l; i++) {
			for(int j=y; j<y+l; j++) {
				if(tmp != arr[i][j]) return false;
			}
		}		
		
		return true;
	}
}
