package greedy;

import java.util.*;

public class BOJ14501 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] t = new int[n+1], p = new int[n+1];
		for(int i=1; i<=n; i++) {
			t[i] = sc.nextInt();
			p[i] = sc.nextInt();
		}

		//dp[n] : n번째 날에 일을 할 때 벌 수 있는 최고 금액
		int[] dp = new int[n+1];
		int ans = dp[0];
		for(int i=1; i<=n; i++) {
			if(i + t[i] <= n+1) {
				for(int j=0; j<i; j++) 
					if(j + t[j] <= i) dp[i] = Math.max(dp[i], dp[j] + p[i]);
				
				ans = Math.max(ans, dp[i]);
			}
		}
		System.out.println(ans);
	}
}
