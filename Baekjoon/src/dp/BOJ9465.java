package dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9465 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			int[][] stickers = new int[2][n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				stickers[0][j] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				stickers[1][j] = Integer.parseInt(st.nextToken());

			bw.write(dp(stickers, n) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	public static int dp(int[][] stickers, int n) {
		if(n == 1){
			return Math.max(stickers[0][0], stickers[1][0]);
		}else if(n == 2){
			return Math.max(stickers[0][0] + stickers[1][1], stickers[1][0] + stickers[0][1]);
		}
		
		int[][] dp = new int[2][n];
		dp[0][0] = stickers[0][0];
		dp[1][0] = stickers[1][0];
		
		for(int i=1; i<n; i++){
			if(i == 1){
				dp[0][i] = dp[1][i-1] + stickers[0][i];
				dp[1][i] = dp[0][i-1] + stickers[1][i];
			}else{
				int max = Math.max(dp[0][i-2], dp[1][i-2]);
				dp[0][i] = Math.max(max, dp[1][i-1]) + stickers[0][i];
				dp[1][i] = Math.max(max, dp[0][i-1]) + stickers[1][i];
			}
		}
		
		int max1 = Math.max(dp[0][n-2], dp[1][n-2]);
		int max2 = Math.max(dp[0][n-1], dp[1][n-1]);
 		return Math.max(max1, max2);
	}

}
