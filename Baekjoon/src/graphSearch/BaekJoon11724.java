package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11724 {
	
	static int n, m;
	static int[] visited;
	static int[][] adjacent;
		
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		if(m == 0) {
			System.out.println(0);
		}else {
			int res = 0;
			
			visited = new int[n+1];
			adjacent = new int[n+1][n+1];
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int i1 = Integer.parseInt(st.nextToken());
				int i2 = Integer.parseInt(st.nextToken());
				
				adjacent[i1][i2] = adjacent[i2][i1] = 1;
			}
			
			for(int i=1; i<n+1; i++) {
				if(visited[i] != 1 ) {
					res++;
					dfs(i);
				}
			}
			System.out.println(res);
		}
	}
	
	public static void dfs(int i) {
		if(visited[i] == 1) return;
 		
		visited[i] = 1;
		
		for(int c=0; c<n+1; c++) {
			if(adjacent[i][c] == 1 && visited[c] != 1) {
				dfs(c);
			}
		}
			
	}
}



