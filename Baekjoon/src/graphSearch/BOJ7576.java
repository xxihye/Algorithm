package graphSearch;

import java.util.*;

public class BOJ7576 {

	static int n, m, res;
	static int[][] arr, visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt(); n = sc.nextInt();
		res = Integer.MAX_VALUE;
		
		arr = new int[n][m]; 
		visited = new int[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) arr[i][j] = sc.nextInt();
		}
		
		bfs();
	}
	
	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 1) queue.add(new int[] {i, j});
			}
		}
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0], y = temp[1];
			
			for(int d=0; d<4; d++) {
				int r = x + dir[d][0], c = y + dir[d][1];
				
				if(r < 0 || c < 0 || r >= n || c >= m) continue;
				if(arr[r][c] != 0) continue;	
				
				arr[r][c] = arr[x][y] + 1;
				queue.add(new int[] {r, c});
			}
		}
		
		int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) { 
                	System.out.println(-1);
                    return;
                }
                max = Math.max(max, arr[i][j]);
            }
        }
        
        System.out.println(max - 1);
        return;
	}
}
