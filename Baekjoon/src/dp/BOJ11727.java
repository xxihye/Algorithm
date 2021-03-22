package dp;

import java.util.Scanner;

public class BOJ11727 {

	static int[] dp;
	static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		if(n == 1) {
			System.out.println(1);
		}else if(n == 2) {
			System.out.println(3);
		}else {
			dp = new int[n+1];
			dp[1] = 1;
			dp[2] = 3;
			dp();
		} 
		
	}
	
	private static void dp() {
		for(int i=3; i<=n; i++) {
			//dp[i]
			dp[i] = (dp[i-1] + 2*(dp[i-2])) % 10007;
		}
		
		System.out.println(dp[n]);
	}
}

