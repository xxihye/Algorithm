package swTest;

import java.io.*;
import java.util.*;

public class BOJ14499 {
	
	static int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //동서북남 : 우좌상하
	static int[] dice = new int[6]; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			x = Integer.parseInt(st.nextToken()),
			y = Integer.parseInt(st.nextToken()),
			k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) q.offer(Integer.parseInt(st.nextToken()));
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int d = q.poll();
			int r = x + dir[d][0], c = y + dir[d][1];

			if(r < N && r >= 0 && c < M && c >= 0) {
				rollDice(d);
			
				x = r;
				y = c;
				
				if(map[x][y] != 0) {
					dice[0] = map[x][y];
					map[x][y] = 0;
				}else map[x][y] = dice[0];
				sb.append(dice[5] + "\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
		
	}
	
	//주사위가 돌아가지 않고 숫자만 돌려지게함
	public static void rollDice(int cmd) {
		
		//주사위의 숫자를 temp[]에 복사
		int[] temp = new int[6];
		for(int i=0; i<6; i++) temp[i] = dice[i];

		//이동명령 방향에 따라 주사위에 숫자를 바꿔 저장
		switch(cmd){
		case 1:
			//동
			dice[0] = temp[2];
			dice[2] = temp[5];
			dice[5] = temp[3];
			dice[3] = temp[0];
			break;
		case 2:
			//서
			dice[0] = temp[3];
			dice[3] = temp[5];
			dice[5] = temp[2];
			dice[2] = temp[0];
			break;
		case 3:
			//북
			dice[0] = temp[1];
			dice[1] = temp[5];
			dice[4] = temp[0];
			dice[5] = temp[4];
			break;
		case 4:
			//남
			dice[0] = temp[4];
			dice[1] = temp[0];
			dice[4] = temp[5];
			dice[5] = temp[1];
			break;
		}
	}
	
}

