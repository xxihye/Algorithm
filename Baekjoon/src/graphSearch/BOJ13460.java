package graphSearch;

import java.util.*;

public class BOJ13460 {

	static int N, M;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][][][] visit;
	static Ball b;

	static class Ball {
		int[] Red;
		int[] Blue;
		int move;

		public Ball() {
			this.move = 0;
		}

		public Ball(Ball now) {
			this.Red = now.Red.clone();
			this.Blue = now.Blue.clone();
			this.move = now.move;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        // N, M (3 �� N, M �� 10)
        N = sc.nextInt();
        M = sc.nextInt();

		map = new char[N][M];
		b = new Ball();
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					b.Red = new int[] { i, j };
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					b.Blue = new int[] { i, j };
					map[i][j] = '.';
				}
			}
		}
		
		bfs();
	}
	
	private static void bfs() {
		visit = new boolean[N][M][N][M];
		Queue<Ball> q = new LinkedList<>();
		q.offer(b);
		visit[b.Red[0]][b.Red[1]][b.Blue[0]][b.Blue[1]] = true;
		while (!q.isEmpty()) {
			Ball now = q.poll();

			// ����
			if (map[now.Blue[0]][now.Blue[1]] == 'O' || now.move == 11) continue;

			// ����!
			if (map[now.Red[0]][now.Red[1]] == 'O') {
				System.out.println(now.move);
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				Ball next = move(dir, now);
				if (visit[next.Red[0]][next.Red[1]][next.Blue[0]][next.Blue[1]]) continue;
				
				visit[next.Red[0]][next.Red[1]][next.Blue[0]][next.Blue[1]] = true;
				q.offer(next);
			}
		}
		
		System.out.println("-1");
		return;
	}

	private static Ball move(int dir, Ball now) {
		Ball next = new Ball(now);
		boolean flag = false;
		
		switch (dir) {
		case 0: // ��
			if (next.Red[0] < next.Blue[0]) flag = true;
			break;
		case 1: // ��
			if (next.Red[0] > next.Blue[0]) flag = true;
			break;
		case 2: // ��
			if (next.Red[1] < next.Blue[1]) flag = true;
			break;
		case 3: // ��
			if (next.Red[1] > next.Blue[1]) flag = true;
			break;
		}

		int nr = 0, nc = 0;
		if (!flag) {
			// �Ķ��� ���� �����̴� ���
			nr = next.Blue[0] + dr[dir];
			nc = next.Blue[1] + dc[dir];
			while (map[nr][nc] == '.') {
				nr += dr[dir];
				nc += dc[dir];
			}
			
			if (map[nr][nc] == 'O') next.Blue = new int[] { nr, nc };
			else next.Blue = new int[] { nr - dr[dir], nc - dc[dir] };

			nr = next.Red[0] + dr[dir];
			nc = next.Red[1] + dc[dir];
			while (map[nr][nc] == '.') {
				if (nr == next.Blue[0] && nc == next.Blue[1]) break;
				nr += dr[dir];
				nc += dc[dir];
			}
			
			if (map[nr][nc] == 'O') next.Red = new int[] { nr, nc };
			else next.Red = new int[] { nr - dr[dir], nc - dc[dir] };
		}
		else {
			nr = next.Red[0] + dr[dir];
			nc = next.Red[1] + dc[dir];
			while (map[nr][nc] == '.') {
				nr += dr[dir];
				nc += dc[dir];
			}
			
			if (map[nr][nc] == 'O') next.Red = new int[] { nr, nc };
			else next.Red = new int[] { nr - dr[dir], nc - dc[dir] };

			nr = next.Blue[0] + dr[dir];
			nc = next.Blue[1] + dc[dir];
			while (map[nr][nc] == '.') {
				if (nr == next.Red[0] && nc == next.Red[1]) break;
				nr += dr[dir];
				nc += dc[dir];
			}
			
			if (map[nr][nc] == 'O') next.Blue = new int[] { nr, nc };
			else next.Blue = new int[] { nr - dr[dir], nc - dc[dir] };
		}

		next.move++;
		return next;
	}

}
