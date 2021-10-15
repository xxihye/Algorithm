package graphSearch;

import java.io.*;
import java.util.*;

public class BOJ3187 {
	
	static char[][] arr;
	static boolean[][] visit;
	static int[] survive = new int[2];
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int R, C;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		visit = new boolean[R][C];
		String s;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) arr[i][j] = s.charAt(j);
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visit[i][j] && arr[i][j] != '#') bfs(i, j);
			}
		}
		
		System.out.println(survive[0] + " " + survive[1]);
		br.close();
		
	}
	
	public static void bfs(int x, int y) {
		int wolves = 0, sheep = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(arr[now[0]][now[1]] == 'k') sheep++;
			else if(arr[now[0]][now[1]] == 'v') wolves++;
			
			for(int i=0; i<dir.length; i++) {
				int r = now[0] + dir[i][0],
					c = now[1] + dir[i][1];
				
				if(r >= R || r < 0 || c >= C || c < 0) continue;
				
				if(!visit[r][c] && arr[r][c] != '#') {
					visit[r][c] = true;
					q.add(new int[] {r,c});
				}
			}
		}
		
		if(sheep > wolves) survive[0] += sheep;
		else survive[1] += wolves;
	}
}
