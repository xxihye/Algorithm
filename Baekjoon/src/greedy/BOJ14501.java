package greedy;

import java.util.*;

public class BOJ14501 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] tp = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			tp[i][0] = sc.nextInt();
			tp[i][1] = sc.nextInt();
		}

		//dp[n] : n번째 날에 일을 할 때 벌 수 있는 최고 금액
		int[] dp = new int[n+1];
		int ans = dp[0];
		for(int i=1; i<=n; i++) {
			if(i + tp[i][0] <= n+1) {
				for(int j=0; j<i; j++) 
					if(j + tp[j][0] <= i) dp[i] = Math.max(dp[i], dp[j] + tp[i][1]);
				
				ans = Math.max(ans, dp[i]);
			}
		}
		System.out.println(ans);
	}
}
