package dp;

import java.util.Arrays;
import java.util.Scanner;

/*
 * DP[i][j] : 길이가 i이며 j로 끝나는 계단 수의 갯수를 의미
 * 
 * DP[0][X]는 문제에서 길이가 0인 경우는 없으므로 PASS
 * DP[1][1] ~ DP[1][9]는 길이가 1인 계단 수 이므로 0~9이므로 각 1로 초기화
 *  
 * DP[2] ~ DP[N]까지 반복하여 각 값을 계산
 * - DP[X][0] : 0은 차이가 1이 나는 수가 1밖에 없음(-1 제외) 
 * - DP[X][9] : 9는 차이가 1이 나는 수가 8밖에 없음(10 제외)
 * 따라서 DP[X][0]는 DP[X-1][1], DP[X][9]는 DP[X-1][8]
 * 
 * - DP[X][Y] : Y(=1~8)는 차이가 1이 나는 수가 2개씩 있음.. Y-1, Y+1
 * 따라서 DP[X][Y] = DP[X-1][Y-1] + DP[X-1][Y+1]  
 * 
 * 문제에서  1,000,000,000으로 나눈 나머지를 출력하라 하였으므로
 * 각각 DP[i][j]를 저장할 때 1,000,000,000으로 나눈 나머지를 저장
 * 또한, 길이가 N인 계단 수의 갯수를 출력하는 것이므로 DP[N][0] ~ DP[N][9]의 값을 모두 더함
 * 합 역시 1,000,000,000으로 나눈 나머지를 출력
 */

public class BOJ10844 {
	
	static long[][] dp;
	static int n;
	static final long MOD = 1000000000L;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		dp = new long[n+1][10];
		
		System.out.println(dp());
	}
	
	private static long dp() {
		
		for(int i=1; i<10; i++) 
			dp[1][i] = 1;
		
		for(int i=2; i<=n; i++) {
			for(int j=0; j<10; j++) {
				if(j == 0) dp[i][j] = dp[i-1][j+1] % MOD;
				else if(j == 9) dp[i][j] = dp[i-1][j-1] % MOD;
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
			}
		}
		
		return Arrays.stream(dp[n]).sum() % MOD;
	}
}
