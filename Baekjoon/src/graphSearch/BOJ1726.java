package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {
	
	static BufferedReader br;
	static int[][] dirArr = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static int bfs(Move start, Move end, int[][] tramRoad) {
		int N = tramRoad.length, M = tramRoad[0].length;
		int res = 0;
		boolean[][][] visit = new boolean[N][M][4];
		Queue<Move> queue = new LinkedList<>();
		queue.add(start);
		visit[start.r][start.c][start.dir] = true;
		
		while(!queue.isEmpty()) {
			Move p = queue.poll();

			int r = p.r, c = p.c, dir = p.dir, cnt = p.cnt;
			if(r == end.r && c == end.c && dir == end.dir) return cnt;
			
			// 현재 바라본 방향에서 1, 2, 3칸 이동 명령
			for(int i = 1; i <= 3; i++) {
				int nr = r + dirArr[dir][0] * i;  // 현재 방향으로 i칸 이동
				int nc = c + dirArr[dir][1] * i;
				if(0 <= nr && nr < N && 0 <= nc && nc < M) {  // 범위 체크
					if(tramRoad[nr][nc] == 0 && !visit[nr][nc][dir]) {
						queue.add(new Move(nr, nc, dir, cnt + 1));
						visit[nr][nc][dir] = true;
					}else if(tramRoad[nr][nc] == 1) break;
				}
			}
			
			// 현재 방향을 제외한 모든 방향으로 회전
			for(int i = 0; i < 4; i++) {
				if(dir != i && !visit[r][c][i]) {
					int inst = 1;
					if(dir == 0 && i == 1) inst++;
					else if(dir == 1 && i == 0) inst++;
					else if(dir == 2 && i == 3) inst++;
					else if(dir == 3 && i == 2) inst++;
					
					queue.add(new Move(r, c, i, cnt + inst));
					visit[r][c][i] = true;
				}
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] tramRoad = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) tramRoad[i][j] = s.charAt(j * 2) - '0';
		}
		
		st = new StringTokenizer(br.readLine());
		Move begin = new Move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		st = new StringTokenizer(br.readLine());
		Move end = new Move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		
		System.out.println(bfs(begin, end, tramRoad));
	}
	
	static class Move {
		int r, c, dir, cnt;
		Move(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}