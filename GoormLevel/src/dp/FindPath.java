package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindPath {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());
		int r = 2*(c-1) + 1;
		
		int[][] arr = new int[r][c+1];
		int[][] dp = new int[r][c+1];
		
		StringTokenizer st;
		for(int i=0; i<r; i++){
			st = new StringTokenizer(br.readLine());
			int idx = 1;
			while(st.hasMoreTokens()){
				arr[i][idx++] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][1] = arr[0][1];
		for(int i=1; i<r-1; i++){
			for(int j=1; j<c; j++){
				if(arr[i][j] == 0) continue;
				
				if(i < c) dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i-1][j-1]) + arr[i][j]);
				else dp[i][j] = Math.max(dp[i][j] , Math.max(dp[i-1][j], dp[i-1][j+1]) + arr[i][j]);
			}
		}
		
		System.out.println(Math.max(dp[r-2][1] , dp[r-2][2]) + arr[r-1][1]);
	}
}
