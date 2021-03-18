package dp;

import java.util.Scanner;

public class BOJ1463 {
	
	static int[] dp;
	static int num;
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		
		Scanner sc = new Scanner(System.in);
		
		num = sc.nextInt();
		
		if(num == 2 || num == 3) System.out.println(1);
		else if(num == 1) System.out.println(0);
		else {
			dp = new int[num+1];
			dp[2] = 1;
			dp[3] = 1;
			
			dp();
		}
	}

	//3가지 방법 중 연산 값이 제일 작은 애로 선택
	public static void dp() {
		for(int i=4; i<=num; i++) {
			int tmp = Integer.MAX_VALUE;
			
//			1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
			if(i % 3 == 0) tmp = dp[i/3] + 1;
			
//			2. X가 2로 나누어 떨어지면, 2로 나눈다.
			if(i % 2 == 0) tmp = Math.min(dp[i/2] + 1, tmp);
			
//			3. 1을 뺀다.
			dp[i] = Math.min(tmp, dp[i-1] + 1);
		}
		
		System.out.println(dp[num]);
	}
}

