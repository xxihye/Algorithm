package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {
	
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //동 서 남 북
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()),
		    n = Integer.parseInt(st.nextToken());
		
		int[][] tramRoad = new int[m][n];
		String s;
		for(int i=0; i<m; i++){
			s = br.readLine();
			for(int j=0; j<n; j++) tramRoad[i][j] = s.charAt(j * 2) - '0';
		}
		
		Move[] points = new Move[2];
		for(int i=0; i<points.length; i++){
			st = new StringTokenizer(br.readLine());
			points[i] = new Move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0, 0,Integer.parseInt(st.nextToken()) - 1);
		}
		
		int res = findMinMoveCnt(tramRoad, points);
		System.out.println(res);
	}
	
	private static int findMinMoveCnt(int[][] tramRoad, Move[] points){
		int min = Integer.MAX_VALUE;
		int n = tramRoad.length,
		    m = tramRoad[0].length;
		
		Move endPoint = points[1];
		boolean[][][] visit = new boolean[n][m][4];
		Queue<Move> queue = new LinkedList<>();
		queue.add(points[0]);
		visit[points[0].x][points[0].y][points[0].dir] = true;
		
		while(!queue.isEmpty()){
			Move now = queue.poll();
			
			//도착지점이면 명령 횟수를 저장하고 반복문 탈출
			if(now.x == endPoint.x && now.y == endPoint.y){
				int moveCnt = now.moveCnt % 3 == 0 ? now.moveCnt / 3 : now.moveCnt / 3 + 1;
				int dirOrderCnt = (endPoint.dir == now.dir) ? 0 : getDirOrderCnt(endPoint.dir, now.dir);
				min = now.orderCnt + dirOrderCnt + moveCnt;
				break;
			}
			
			for(int d=0; d<dir.length; d++){
				int x = now.x + dir[d][0], y = now.y + dir[d][1];
				
				//범위를 벗어나거나 이미 방문한 경우 무시하고 넘어감
				if(x < 0 || x >= n || y < 0 || y >= m || tramRoad[x][y] == 1 || visit[x][y][now.dir]) continue;
				
				int dirOrderCnt = (d == now.dir) ? 0 : getDirOrderCnt(d, now.dir);
				Move move;
				if(dirOrderCnt == 0){
					//이동방향과 가르키고 있던 방향이 같다면 움직이기
					if(now.moveCnt + 1 == 3) move = new Move(x, y, now.orderCnt + 1, 0,  d);
					else move = new Move(x, y, now.orderCnt, now.moveCnt + 1,  d);
				}else{
					//이동방향과 가르키고 있던 방향이 다르다면 회전 명령 횟수와 기존에 움직였던 칸에 대한 명령 수 더해주기
					int moveCnt = now.moveCnt % 3 == 0 ? now.moveCnt / 3 : now.moveCnt / 3 + 1;
					move = new Move(x, y, now.orderCnt + dirOrderCnt + moveCnt, 1, d);
				}
				
				queue.offer(move);
				visit[x][y][d] = true;
			}
		}
		
		return min;
	}
	
	private static int getDirOrderCnt(int d, int nowDir){
		if(nowDir < 2){
			if(d >= 2) return 1;
			else return 2;
		}else{
			if(d < 2) return 1;
			else return 2;
		}
	}
	
	/**
	 * orderCnt : 실제 명령 횟수
	 * moveCnt : 한 방향으로 움직인 칸 수(3칸마다 초기화)
	 */
	static class Move{
		int x, y, orderCnt, moveCnt, dir;
		
		public Move(int x, int y, int orderCnt, int moveCnt, int dir) {
			this.x = x;
			this.y = y;
			this.orderCnt = orderCnt;
			this.moveCnt = moveCnt;
			this.dir = dir;
		}
	}
}

