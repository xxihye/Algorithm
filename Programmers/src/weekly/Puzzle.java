package weekly;

import java.util.*;

public class Puzzle {
	
	HashMap<String, Point> blockStore = new HashMap<>(); //채워야할 퍼즐 빈칸
	int cnt = 0;
	ArrayList<Point> block; //퍼즐 조각 하나의 좌표들
	int[] dr = {1, -1, 0, 0};
	int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		int[][] gameBoard = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}},
				table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
		System.out.println(p.solution(gameBoard, table));
	}
	
	public int solution(int[][] game_board, int[][] table) {
		
		int answer = 0;
		
		for(int i=0; i<game_board.length; i++)
			for(int j=0; j<game_board.length; j++)
			game_board[i][j] = game_board[i][j] == 0 ? 1 : 0;
			
		for(int i=0; i<game_board.length; i++) {
			for(int j=0; j<game_board.length; j++) {
				if(game_board[i][j] == 1) {
					//같은 문자열인 퍼즐 빈칸의 갯수를 하나 더하고 다시 저장
					//cnt : bfs를 통해 알아낸 빈칸의 크기
					String blockString = bfs(game_board, i, j);
					Point bp = blockStore.getOrDefault(blockString, new Point(0, cnt)); 
					bp.x++;
					blockStore.put(blockString, bp);
				}
			}
		}	
			
		//0 -> 90 -> 180 -> 270
		for(int r = 0; r < 4; r++) {
			int[][] tempTable = new int[table.length][table.length];
			for(int d=0; d< table.length; d++) tempTable[d] = table[d].clone();
			
			for(int i=0; i<tempTable.length; i++) {
				for(int j=0; j<tempTable.length; j++) {
					if(tempTable[i][j] == 1) {
						String blockString  = bfs(tempTable, i, j);
						if(blockStore.containsKey(blockString)) {
							for(Point bp : block) table[bp.x][bp.y] = 0;
							Point p = blockStore.get(blockString);
							answer += p.y;
							
							if(--p.x == 0) blockStore.remove(blockString);
							else blockStore.put(blockString, p);
						}
					}
				}
			}
			
			if(r < 3) table = rotate(table);
		}
		
		return answer;
	}
	
	//테이블 회전시키기
	public int[][] rotate(int[][] board){
		int[][] newBoard = new int[board.length][board.length];
		
		for(int i=0; i<board.length; i++) 
			for(int j=0; j<board.length; j++) 
				newBoard[j][board.length - i - 1] = board[i][j];
			
		return newBoard;
	}
	
	public String bfs(int[][] board, int x, int y) {
		int minX = 987654321, minY = 987654321;
		int maxX = 0, maxY = 0;
		
		Queue<Point> queue = new LinkedList<>();
		block = new ArrayList<>();
		queue.add(new Point(x, y));
		board[x][y] = 0;
		cnt = 0;
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			cnt++;
			minX = Math.min(now.x , minX);
			minY = Math.min(now.y , minY);
			maxX = Math.max(now.x , maxX);
			maxY = Math.max(now.y, maxY);
			block.add(now);
			
			for(int i=0; i<4; i++) {
				int nowx = now.x + dr[i],
					nowy = now.y + dc[i];
				
				if (nowx < 0 || nowy < 0 || nowx >= board.length || nowy >= board.length)
					continue;
				if (board[nowx][nowy] == 0)
					continue;
				board[nowx][nowy] = 0;
				queue.add(new Point(nowx, nowy));
			}
		}
		return makeString(minX, minY, maxX, maxY, block);
	}
	
	
	
	public String makeString(int minX,int minY,int maxX,int maxY, ArrayList<Point> block) {
		int[][] blockArr = new int[maxX - minX + 1][maxY - minY + 1];
		for(Point p : block) 
			blockArr[p.x - minX][p.y - minY] = 1;
		
		StringBuilder sb= new StringBuilder();
		for(int i=0; i<blockArr.length; i++) {
			for(int j=0; j<blockArr[0].length; j++)
				sb.append(blockArr[i][j]);
			sb.append('n');
		}
		
		return sb.toString();
	}
}

class Point {
	int x; //모형의 갯수 
	int y;   //모형의 크기
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}