package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2178 {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	public static void main(String[] args) throws IOException {
		// 입력받아 map 채우기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = charArr[j] - '0';
			}

		}

//		bfs 호출
		bfs();

		System.out.println(map[n-1][m-1]);
	}

	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0 });

		while (!queue.isEmpty()) {
			int[] loc = queue.poll();
			visited[loc[0]][loc[1]] = true;

			// 인접 노드 찾기 (상하좌우)
			for (int i = 0; i < 4; i++) {
				int r = loc[0] + direction[i][0];
				int c = loc[1] + direction[i][1];

				if (r >= 0 && r < n && c >= 0 && c < m) {
					// 방문하지 않은 인접노드 일 때에만
					if (map[r][c] != 0 && !visited[r][c]) {
						queue.add(new int[] { r, c }); // 큐에 넣기
						visited[r][c] = true; // 방문표시
						map[r][c] = map[loc[0]][loc[1]] + 1; // 부모노드의 움직인 수 + 1;
					}
				}
			}
		}
	}
}
