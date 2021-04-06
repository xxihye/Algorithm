package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465 {

	static int[][] stickers;
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine());
			stickers = new int[2][n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				stickers[0][j] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				stickers[1][j] = Integer.parseInt(st.nextToken());

			dp();
		}
	}
	
	
	public static void dp() {
		stickers[0][1] += stickers[1][0];
		stickers[1][1] += stickers[0][0];
		
		for(int i=2; i<n; i++) {
			stickers[0][i] += Math.max(stickers[1][i-2], stickers[1][i-1]); 
			stickers[1][i] += Math.max(stickers[0][i-2], stickers[0][i-1]);
		}
		
		System.out.println(Math.max(stickers[0][n-1], stickers[1][n-1]));
				
	}

}
