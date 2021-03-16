package graphsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon7562 {

	static int[][] chess;
	static int l;
	static int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
	static int[] goal = new int[2];
	static int[] loc = new int[2];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<c; i++) {
			st = new StringTokenizer(br.readLine());
			
			l = Integer.parseInt(st.nextToken());
			chess = new int[l][l];
			
			st = new StringTokenizer(br.readLine());
			loc[0] = Integer.parseInt(st.nextToken());
			loc[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			goal[0] = Integer.parseInt(st.nextToken());
			goal[1] = Integer.parseInt(st.nextToken());
			
			if(loc[0] == goal[0] && loc[1] == goal[1]) System.out.println(0);
			else bfs();
		}
		
	}

	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(loc);
		chess[loc[0]][loc[1]] = 1;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int i=0; i<8; i++) {
				int r = temp[0] + dir[i][0];
				int c = temp[1] + dir[i][1];
				
				if(r == goal[0] && c == goal[1]) {
					System.out.println(chess[temp[0]][temp[1]]);
					return;
				}
				
				if(r >= 0 && c >= 0 & r < l && c < l && chess[r][c] == 0) {
					q.add(new int[] {r, c});
					chess[r][c] = chess[temp[0]][temp[1]] + 1;
				}
			}
			
		}
	}
}
