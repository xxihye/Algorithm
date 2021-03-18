package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
	
	static int[][] triangle;
	static int l;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		l = Integer.parseInt(br.readLine());
		
		triangle = new int[l][l];
		
		for(int i=0; i<l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<=i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dp());
	}
	
	public static int dp() {
        int[][] dp = triangle.clone();
        
        for(int i=triangle.length-2; i>=0; i--) {
        	for(int j=0; j<=i; j++) {
        		dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j];
        	}
        }
        
        return dp[0][0];
	 }
}
