package dp;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1699 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] dp = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			dp[i] = i; // 1^2으로만 이루어졌다고 가정(최대)
			for (int j = 1; j * j <= i; j++) {
				//i보다 작은 제곱수들을 이용하여 비교를 해서 가장 작은 항의 수를 저장
				dp[i] = Math.min(dp[i], dp[i - (j * j)] + 1);
			}
		}
		
		System.out.println(dp[n]);

	}

}
