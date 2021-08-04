package exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOARDCOVER {
	
	static int H, W, count ;
	static int[][] board;
	static int[][][] type = {{{ 0, 0 }, { 1, 0 }, { 0, 1 }}, 
							 {{ 0, 0 }, { 1, 0 }, { 1, -1 }},
							 {{ 0, 0 }, { 1, 0 }, { 1, 1 }},
							 {{ 0, 0 }, { 0, 1 }, { 1, 1 }}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine().trim());
		while (C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			count = 0;
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			board = new int[H][W];

			int cntOfWhite = 0;
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					board[i][j] = str.charAt(j) == '#' ? 1 : 0;
					if (board[i][j] == 0) cntOfWhite++;
				}
			}

			if (cntOfWhite % 3 != 0) {
				System.out.println(0);
				continue;
			}
			
			dfs();
			System.out.println(count);
		}
	}

	private static void dfs() {
		int[] node = findWhite();
		if (node == null) {
			count++;
			return;
		}

		outLoop: 
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				int nextX = node[0] + type[i][j][0];
				int nextY = node[1] + type[i][j][1];
				
				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) continue outLoop;
				if (board[nextX][nextY] == 1) continue outLoop;
			}

			set(node, i, 1);
			dfs();
			set(node, i, 0);
		}

	}

	private static void set(int[] node, int typeNum, int num) {
		for (int j = 0; j < 3; j++) {
			int nextX = node[0] + type[typeNum][j][0];
			int nextY = node[1] + type[typeNum][j][1];
			board[nextX][nextY] = num;
		}
	}

	private static int[] findWhite() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (board[i][j] == 0) return new int[] { i, j };
			}
		}
		return null;
	}

}