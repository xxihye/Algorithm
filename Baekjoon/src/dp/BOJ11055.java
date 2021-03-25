package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11055 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n];
		int[] seq = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");;
		for(int i=0; i<n; i++) {
			seq[i] = Integer.parseInt(st.nextToken()); 
		}
		
		dp[0] = seq[0];
		int max = dp[0];
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(seq[j] < seq[i] && dp[i] < dp[j] ) {
					dp[i] = dp[j];	
				}
			}
			dp[i] += seq[i];
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(max);
		
	}

}
