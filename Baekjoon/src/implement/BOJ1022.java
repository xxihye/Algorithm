package implement;

import java.io.*;
import java.util.*;

public class BOJ1022 {
	
	static int[][] map;
	static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //우상좌하
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int r1 = sc.nextInt();
		int c1 = sc.nextInt();
		int r2 = sc.nextInt();
		int c2 = sc.nextInt();
		
		int h = r2 - r1 + 1;
		int w = c2 - c1 + 1;
		map = new int[h][w];
		
		int x = 0, y = 0; //가상의 배열의 인덱스
		int d = 0; //진행 방향을 위한 배열의 인덱스(반시계 방향 - 우상좌하)
		int num = 1; //저장될 숫자
		int cnt = 0, wCnt = 0, dCnt = 1; //한 방향으로 움직인 횟수, 배열을 채운 갯수, 한 방향으로 움직여야하는 횟수 
		while(true) {
			
			if(wCnt >= h*w) break;
			
			if(x >= r1 && x <= r2 && y >= c1 && y <= c2) {
				map[x - r1][y - c1] = num; //map배열에 맞도록 인덱스처리 후 숫자 저장 
				wCnt++;
			}
			
			num++;
			cnt++;
			
			//소용돌이로 모양으로 저장되므로 다음에 숫자를 저장한 인덱스 
			x += dir[d][0];
			y += dir[d][1]; 
			
			if(cnt == dCnt) {
				cnt = 0;
				//좌나 우로 값을 채울 때 칸이 하나씩 늘어남
				if(d == 1 || d == 3) dCnt++;
				d = (d+1) % 4; // 다음 방향
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int blank = (int)(Math.log10(num)+1); //최대숫자의 자릿수
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int len = (int)(Math.log10(map[i][j])+1); //현재 숫자의 자릿수
				for (int k = 0; k < blank-len; k++) sb.append(" "); //모자른 만큼 빈칸 채우기
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
