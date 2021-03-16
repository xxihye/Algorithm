package graphsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1012 {
	
	static int[][] cabbage;
	static int n, m;
	static int cabbages;
	static int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			cabbages = Integer.parseInt(st.nextToken());
			
			cabbage = new int[n][m];
			
			for(int j=0; j<cabbages; j++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				cabbage[x][y] = 1;
			}
			
			int res = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(cabbage[i][j] == 1) {
						res++;
						dfs(i, j);
					}
				}
			}
			
			System.out.println(res);
		}
		
		
	}
	
	public static void dfs(int x, int y) {
		cabbage[x][y] = 0;
		//상하좌우 검색해서 
		for(int i=0; i<4; i++) {
			int r = x + direction[i][0];
			int c = y + direction[i][1];
			
			if(r >=0 && r < n && c >=0 && c < m) {
				if(cabbage[r][c] == 1) {
					dfs(r, c);
				}
					
			}
		}
	}
}
