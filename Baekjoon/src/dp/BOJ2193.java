package dp;

import java.util.Scanner;

public class BOJ2193 {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		if(n == 1) System.out.println(1);
		else {
			long[] dp = new long[n+1];
			dp[1] = 1;
			
			for(int i=2; i<=n; i++) {
				dp[i] = dp[i-2] + dp[i-1];
			}
			
//			System.out.println(dp[n]);
			System.out.println(dp(n));
		} 
	}
	
	public static long dp(int n) {
		
		long[][] dp = new long[n+1][2];
		
		dp[1][1] = 1;
		
		for(int i=2; i<=n; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}
		
		return dp[n][0] + dp[n][1];
	}
	
	

}
