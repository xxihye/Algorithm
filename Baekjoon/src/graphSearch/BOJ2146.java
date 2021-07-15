package graphSearch;

import java.io.*;
import java.util.*;

public class BOJ2146 {
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int min, n, comp = 1;
	static int[][] arr, component;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		component = new int[n][n]; 
		min = 2 * n;
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 1 && component[i][j] == 0) {
					dfs(i, j);
					comp++;
				}
			}
		}
		
		bfs();
		System.out.println(min);

	}
	
	
	public static void dfs(int x, int y) {
		component[x][y] = comp;
		
		for(int i = 0; i<4; i++) {
			int r = x + dir[i][0], c = y + dir[i][1];
			if(r >= 0 && r < n && c >= 0 && c < n) {
				if(arr[r][c] == 1 && component[r][c] == 0) dfs(r, c);
			}
		}
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 1) q.add(new int[] {i, j});
			}
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int idx = 0; idx<4; idx++) {
				int r = temp[0] + dir[idx][0], c = temp[1] + dir[idx][1];
					
				if(r < n && r >= 0 && c < n && c >= 0) {
					if(arr[r][c] == 0) { 
						arr[r][c] = arr[temp[0]][temp[1]] + 1;
						component[r][c] = component[temp[0]][temp[1]];
						q.add(new int[] {r, c});
					}else {
						if(component[r][c] == component[temp[0]][temp[1]]) continue;
						else {
							int length = (arr[r][c] - 1) + arr[temp[0]][temp[1]] - 1;
							min = Math.min(min, length);
						}
					}
					
				}
				
			}
		}
	}
}
