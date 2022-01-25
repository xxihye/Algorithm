package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TRIPATHCNT {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		TRIPATHCNT tri = new TRIPATHCNT();
		StringBuilder sb = new StringBuilder();
		
		int c = Integer.parseInt(br.readLine());
		for(int i=1; i<=c; i++){
			int n = Integer.parseInt(br.readLine());
			int[][] triangle = tri.setTriangle(n);
			sb.append(tri.getPathCnt(n, triangle));
			if(i < c) sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private int[][] setTriangle(int n) throws IOException {
		int[][] triangle = new int[n+1][n+1];
		for(int i=1; i<=n; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=i; j++) triangle[i][j] = Integer.parseInt(st.nextToken());
		}
		
		return triangle;
	}
	
	
	private long getPathCnt(int n, int[][] triangle){
		int[][] cache = new int[n+1][n+1];
		cache[1][1] = 1;
		for(int i=2; i<=n; i++){
			for(int j=1; j<=i; j++) triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
			
			for(int j=1; j<=i; j++) {
				if(triangle[i-1][j-1] == triangle[i-1][j]) cache[i][j] = cache[i-1][j-1] + cache[i-1][j];
				else if(triangle[i-1][j-1] > triangle[i-1][j] ) cache[i][j] = cache[i-1][j-1];
				else cache[i][j] = cache[i-1][j];
			}
		}
		
		int max = Arrays.stream(triangle[n]).max().getAsInt();
		int res = 0;
		for(int j=1; j<=n; j++){
			if(triangle[n][j] == max) res += cache[n][j];
		}
		return res;
	}

}
