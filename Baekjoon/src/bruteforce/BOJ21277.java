package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21277 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int[][] arr = new int[151][151]; 
	static int[][] puzzleA, puzzleB;
	
	static int ans = 999999;
	
	static int n1, n2, m1, m2;
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		n1 = Integer.parseInt(st.nextToken());
		m1 = Integer.parseInt(st.nextToken());
		puzzleA = new int[51][51];
		input(n1, m1, puzzleA);
		
		st = new StringTokenizer(br.readLine());
		n2 = Integer.parseInt(st.nextToken());
		m2 = Integer.parseInt(st.nextToken());
		puzzleB = new int[51][51];
		input(n2, m2, puzzleB);
		
		
		/**
		 * 퍼즐 2는 가운데 고정시켜두기
		 * 현재 퍼즐의 각 꼭지점 
		 * = (50, 50), (50, 49 + m2), (49 + n2, 50), (49 + n2, 49 + m2)
		 */
		for(int i=50; i < 50 + n2; i++) 
			for(int j=50; j< 50 + m2; j++) 
				arr[i][j] = puzzleB[i-50][j-50];
		
		/**
		 * for 1 : 퍼즐A을 돌리면서 확인(총 4번)
		 * for 2 : 해당 퍼즐의 첫 칸을 (0, 0) ~ (100, 100)까지 위치시키며 비교
		 */
		for(int r=0; r<4; r++) {
			puzzleA = rotate();
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					go(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static int[][] rotate(){
		int[][] newBoard = new int[51][51];
			
		for(int i=0; i<n1; i++) {
			for(int j=0; j<m1; j++) {
				newBoard[j][m1-1-i] = puzzleA[i][j];
			}
		}
		
		int tmp = n1;
		n1 = m1;
		m1 = n1;
		
		return newBoard;
	}
	
	public static void go(int x, int y) {
		boolean flag = true;
		for(int i=x; i<x+n1; i++) {
			for(int j=y; j<y+m1; j++) {
				if(arr[i][j] == 1 && puzzleA[i-x][j-y] == 1) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			int minY = Math.min(y, 50);
			int maxY = Math.max(y + m1 -1, 49 + m2);
			int minX = Math.min(x , 50);
			int maxX = Math.max(x + n1 -1, 49 + n2);

			int area = (maxY - minY + 1) * (maxX - minX + 1);
			ans = Math.min(ans, area);
		}
		
	}
	
	
	
	public static void input(int x, int y, int[][] puzzle) throws IOException {
		for(int i=0; i<x; i++) {
			String s = br.readLine();
			for(int j=0; j<y; j++) 
				puzzle[i][j] = s.charAt(j) - '0';
		}	
	}
}
