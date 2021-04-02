package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2011 {
	
	static final int MOD = 1000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		if(str.startsWith("0")) {
			System.out.println(0);
			return;
		}
		
		int n = str.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i=1; i<n; i++) {
			char pre = str.charAt(i-1);
			if(str.charAt(i) >= '1' && str.charAt(i) <= '9')
				dp[i+1] = dp[i] % MOD;
			
			if((pre == '1' && str.charAt(i) <= '9') || (pre == '2' && str.charAt(i) <= '6')) 
				dp[i+1] += dp[i-1] % MOD;
			
			dp[i+1] %= MOD;
		}
		
		
		System.out.println(dp[n]);
	}
}
