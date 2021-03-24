package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2156 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n];

		for (int i = 0; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		if (n == 1)
			System.out.println(wine[0]);
		else if (n == 2)
			System.out.println(wine[0] + wine[1]);
		else {
			int[] dp = new int[n];

			dp[0] = wine[0];
			dp[1] = dp[0] + wine[1];
			dp[2] = Math.max(Math.max(wine[1], wine[0]) + wine[2], dp[1]); //3개 중 2개의 합이 제일 큰걸로 저장

			if(n > 3){
				for (int i = 3; i < n; i++) 
					dp[i] = Math.max(dp[i-1], Math.max(dp[i-3] + wine[i-1], dp[i-2]) + wine[i]);
			}
			
			System.out.println(dp[n-1]);
		}

	}

}
