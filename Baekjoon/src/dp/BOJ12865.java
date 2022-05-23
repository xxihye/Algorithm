package dp;

/**
 * https://hoondev.tistory.com/74
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Pair[] obj = new Pair[n+1];
		
		for(int i=1; i<=n; i++){
			st = new StringTokenizer(br.readLine());
			obj[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int[][] dp = new int[n+1][k+1];
		for(int i=1; i<=n; i++){
			for(int j=0; j<=k; j++){
				dp[i][j] = dp[i-1][j];
				int w = obj[i].w;
				int v = obj[i].v;
				if(w <= j) dp[i][j] = Math.max(v + dp[i-1][j - w], dp[i][j]);
			}
		}
		
		for(int[] arr : dp){
			System.out.println(Arrays.toString(arr));
		}
		
		
		System.out.println(dp[n][k]);
	}
	
	static class Pair{
		int w, v;
		
		public Pair(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
}
