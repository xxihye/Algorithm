package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JUMPGAME {
	
	static int[][] board;
	static boolean[][] visited;
	static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine());
		
		while(c-- > 0) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			visited = new boolean[n][n];

			for(int i=0; i<n; i++) {
				String str = br.readLine();
				for(int j=0; j<n; j++) {
					board[i][j] = str.charAt(j*2) - '0';
				}
			}
			
			if(isArrived(0, 0)) System.out.println("YES");
			else System.out.println("NO");
			
			
		}
	}
	
	public static boolean isArrived(int i, int j) {
		if(i == n-1 && j == n-1) return true;
		
		int cnt = board[i][j];
		visited[i][j] = true;
		
		if(i + cnt < n) {
			if(!visited[i+cnt][j] && isArrived(i+cnt, j)) return true;
		}
		
		if(j + cnt < n) {
			if(!visited[i][j+cnt] && isArrived(i, j+cnt)) return true;
		}
		return false;
	}
}
