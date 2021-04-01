package dp;

import java.util.Scanner;

public class BOJ2225 {

	static int MOD = 1000000000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		long[][] dp = new long[k+1][n+1];
		
		
		for(int i=1; i<=k; i++) {
			dp[i][0] = 1;
			for(int j=1; j<=n; j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % MOD;
			}
		}
		
		System.out.println(dp[k][n]);
		

	}

}
