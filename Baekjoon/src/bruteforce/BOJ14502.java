package bruteforce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14502 {
	
	static int N, M, wallCnt = 0, max = 0;
	static int[][] map;
	static ArrayList<int[]> virusList = new ArrayList<>();
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) virusList.add(new int[] {i,j});
				else if(map[i][j] == 1) wallCnt++; //기존에 존재하던 벽 개수
			}
		}
			
		//벽 3개 세우기 
		setWall(0);
		
		System.out.println(max);
	}
	
	
	private static void setWall(int cnt) {
		if(cnt == 3) {
			spreadVirus(); //3개 세웠으니까 바이러스 bfs
			return;
		}
		
		for(int i=0; i<N*M; i++) {
			if(map[i/M][i%M] == 0) {
				map[i/M][i%M] = 1;
				setWall(cnt+1);
				map[i/M][i%M] = 0;
			}
		}
		
	}
	
	private static void spreadVirus() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		int virus = 0;
		
		for(int i=0; i<virusList.size(); i++) {
			q.add(virusList.get(i));
			
			while(!q.isEmpty()) {
				int[] now = q.poll();
				
				for(int d=0; d<dir.length; d++) {
					int r = now[0] + dir[d][0];
					int c = now[1] + dir[d][1];
					
					if(r >= N || r < 0 || c >= M || c < 0) continue;
					
					if(!visit[r][c] && map[r][c] == 0) {
						visit[r][c] = true;
						q.add(new int[] {r,c});
						virus++;
					}
				}
			}
		}
		
		max = Math.max(max, (N*M) - (wallCnt + 3) - virus - virusList.size());
		// 총 개수 - 벽개수 - 퍼진 바이러스 - 원래 바이러스 = 안전영역 개수
	}
}
