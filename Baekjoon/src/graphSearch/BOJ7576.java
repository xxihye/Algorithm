package graphSearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ7576 {

	static int n, m, res;
	static int[][] arr, visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt(); n = sc.nextInt();
		res = 0;
		
		arr = new int[n][m]; visited = new int[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				arr[i][j] = sc.nextInt();
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 1 && visited[i][j] == Integer.MAX_VALUE) bfs(i, j);
			}
		}
		
		for(int[] tmp : visited)
			System.out.println(Arrays.toString(tmp));
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(visited[i][j] == Integer.MAX_VALUE && arr[i][j] != -1) {
					System.out.println("-1"); return;
				}else if(visited[i][j] != Integer.MAX_VALUE){
					res = Math.max(visited[i][j], res);
				}
			}
		}
		
		System.out.println(res);
		
		
	}
	
	public static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {i, j});
		visited[i][j] = 0;
		
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int d=0; d<4; d++) {
				int r = temp[0] + dir[d][0],
					c = temp[1] + dir[d][1];
				
				int cnt = visited[temp[0]][temp[1]] + 1;
				if(r >=0 && c >= 0 && r < n && c < m) {
					if((arr[r][c] == 0 && cnt < visited[r][c])) {
						cnt = visited[temp[0]][temp[1]] + 1;
						queue.add(new int[] {r, c});
						visited[r][c] = cnt;
					}
				}
			}
		}
		
	}
}
