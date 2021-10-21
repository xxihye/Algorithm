package graphSearch;

import java.util.*;

public class BOJ14503 {
	static int N, M, res = 0;
	static int[][] map;
	static boolean[][] visit;
	static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; //ºÏ¼­³²µ¿
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		int x = sc.nextInt(),
			y = sc.nextInt();
		int d = (4 - sc.nextInt()) % 4;
		
		for(int i=0; i<N; i++) 
			for(int j=0; j<M; j++) 
				map[i][j] = sc.nextInt();
		
		int r = x, c = y;
		while(true) {
			
			if(map[x][y] == 0) {
				map[x][y] = 2;
				res++;
			}
			
			boolean allCleaned = true;
			for(int i=1; i<=dir.length; i++) {
				r = x + dir[(d+i)%4][0];
				c = y + dir[(d+i)%4][1];
				
				if(map[r][c] == 0) {
					d = (d+i)%4;
					x = r;
					y = c;
					allCleaned = false;
					break;
				}
			}
			
			if(allCleaned) {
				r = x + dir[(d+2)%4][0];
				c = y + dir[(d+2)%4][1];
				if(map[r][c] != 1) {
					x = r;
					y = c;
				}else {
					System.out.println(res);
					return;
				}
			}
			
		}
			
	}
	
	
}
