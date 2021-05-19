package graphSearch;

import java.io.IOException;
import java.util.Scanner;

public class BOJ4963 {
	
	static int[][] arr;
	static boolean[][] visited;
	static int island; 
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}}; //상하좌우
	static int w, h;
	

	//h : 행, W : 열
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			w = sc.nextInt(); h = sc.nextInt();
			
			if(w == 0) break;
			
			arr = new int[h][w]; visited = new boolean[h][w]; island = 0;
			
			for(int i=0; i<h; i++) 
				for(int j=0; j<w; j++) 
					arr[i][j] = sc.nextInt();
			
//			for(int[] i : arr) {
//				System.out.println(Arrays.toString(i));
//			}
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(!visited[i][j] && arr[i][j] == 1) {
						island++;
						dfs(i, j); 
					}
				}
			}
			
			sb.append(island + "\n");
			
					
		}
		
		System.out.println(sb.toString());

	}
	
	// 
	public static void dfs(int i, int j) {
		visited[i][j] = true;
		
		for(int x=0; x<8; x++) {
			int r = i + dir[x][0] , c = j + dir[x][1];
			if(c >= 0 && c < w && r >= 0 && r < h) {
				if(arr[r][c] == 1 && visited[r][c] == false) {
					dfs(r, c);
				}
			}
		}
	}

}
