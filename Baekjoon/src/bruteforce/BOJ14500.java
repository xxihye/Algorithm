package bruteforce;

import java.util.*;
import java.io.*;

public class BOJ14500 {
	
	static int N, M;
	static int[][] arr;
	static int max = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		getMax();
		
		System.out.println(max);
	}
	
	public static void getMax() {
		int i, j;
		// 2x2
		for(i=0; i<N-1; i++) 
			for(j=0; j<M-1; j++) 
				max = Math.max(max, arr[i][j] + arr[i+1][j] + arr[i][j+1] + arr[i+1][j+1]);
			
		// 1x4
		for(i=0; i<N; i++) 
			for(j=0; j<M-3; j++) 
				max = Math.max(max, arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i][j+3]);
		
		//4x1
		for(j=0; j<M; j++) 
			for(i=0; i<N-3; i++) 
				max = Math.max(max, arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+3][j]);
		
		//3x2
		for(i=0; i<N-2; i++) {
			for(j=0; j<M-1; j++) {
				int sum = arr[i][j]   + arr[i][j+1] 
						+ arr[i+1][j] + arr[i+1][j+1]
						+ arr[i+2][j] + arr[i+2][j+1];
				
				max = Math.max(max, sum - (arr[i][j+1] + arr[i+1][j+1]));
				max = Math.max(max, sum - (arr[i+1][j] + arr[i+2][j]));
				max = Math.max(max, sum - (arr[i][j] + arr[i+1][j]));
				max = Math.max(max, sum - (arr[i][j+1] + arr[i+2][j]));
				max = Math.max(max, sum - (arr[i][j] + arr[i+2][j+1]));
				max = Math.max(max, sum - (arr[i][j] + arr[i+2][j]));
				max = Math.max(max, sum - (arr[i][j+1] + arr[i+2][j+1]));
				max = Math.max(max, sum - (arr[i+1][j+1] + arr[i+2][j+1]));
			}
		}
		
		//2x3
		for(i=0; i<N-1; i++) {
			for(j=0; j<M-2; j++) {
				int sum = arr[i][j] + arr[i][j+1] + arr[i][j+2]
						+ arr[i+1][j] + arr[i+1][j+1] + arr[i+1][j+2];
				
				max = Math.max(max, sum - (arr[i+1][j+1] + arr[i+1][j+2]));
				max = Math.max(max, sum - (arr[i][j] + arr[i][j+1]));
				max = Math.max(max, sum - (arr[i][j] + arr[i+1][j+2]));
				max = Math.max(max, sum - (arr[i+1][j] + arr[i+1][j+2]));
				max = Math.max(max, sum - (arr[i][j] + arr[i][j+2]));
				max = Math.max(max, sum - (arr[i+1][j] + arr[i][j+2]));
				max = Math.max(max, sum - (arr[i][j+1] + arr[i][j+2]));
				max = Math.max(max, sum - (arr[i+1][j] + arr[i+1][j+1]));
			}
		}
	}
}
