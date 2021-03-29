package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] seq = new int[n];
		int[] dp = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < n; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = seq[0];
		int max = dp[0];
		
		/**
		 * dp[i]는 i번째 요소를 포함하여 얻을 수 있는 연속한 요소들의 합 중 제일 큰 것을 저장한 것
		 * 이전 요소를 포함하여 얻은 합에 현재 요소를 더한 값과 현재 요소 자체를 비교하여 더 큰 값을 저장
		 * ->  dp[i] = Math.max(dp[i-1] + seq[i], seq[i])
		 * seq[i]가 dp[i-1] + seq[i] 값보다 크다면 dp[i-1] + seq[i]은 음수값을 가지고있다는 것을 의미함
		 * 
		 * dp[i]를 구함과 동시에 max값을 계산
		 */
		for(int i=1; i<n; i++) {
			dp[i] = Math.max(dp[i-1] + seq[i], seq[i]);
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		
	}

}
