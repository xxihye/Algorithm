package dp;

import java.util.Scanner;

public class BOJ2579 {

	/**
	 * dp[i]는 i번째 계단을 밟았을 때, 얻을 수 있는 최대 점수를 저장한 배열
	 * 
	 * i번째 계단을 밟았을 때의 2가지 경우
	 * 1. i-1, i번째 계단을 연속하여 밟은 경우
	 * - i-2번째 계단은 밟지 못함 
	 * - i-3번째 계단은 밟을 수 있음 = i-3번째까지 얻을 수 있는 최대 점수
	 * => dp[i-3] + stairs[i-1] + stairs[i] 
	 * 
	 * 2. i-1번째 계단을 밟지 않고 i번째 계단을 밟은 경우
	 * - i-1번째 계단은 밟지 못함
	 * - i-2번째 계단은 밟을 수 있음 = i-2번째까지 얻을 수 있는 최대 점수
	 * => dp[i-2] + stairs[i]
	 * 
	 * 두 가지 경우 중 더 큰 값을 저장하면 됨
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] stairs = new int[n];
		
		for(int i=0; i<n; i++) {
			stairs[i] = sc.nextInt();
		}

		if(n == 1) System.out.println(stairs[0]);
		else if(n == 2) System.out.println(stairs[0] + stairs[1]);
		else {
			int[] dp = new int[n];

			dp[0] = stairs[0];
			dp[1] = dp[0] + stairs[1];
			dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
			
			if(n == 3) System.out.println(dp[2]);
			else {
				for(int i=3; i<n; i++) {
					dp[i] = Math.max(stairs[i-1] + dp[i-3], dp[i-2]) + stairs[i];
				}
				
				System.out.println(dp[n-1]);
			}
		}
		
		
		
	}

}
