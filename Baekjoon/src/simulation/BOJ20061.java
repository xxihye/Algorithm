package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20061 {
	
	static class Block{
		int t, x, y;
		
		public Block(int t, int x, int y) {
			this.t = t;
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, score;
	static int[][] greenBoard, blueBoard;
	
	public static void main(String[] args) throws IOException {
		set();

		int res = 0;
		for(int[] green : greenBoard)
			res += Arrays.stream(green).sum();

		for(int[] blue : blueBoard)
			res += Arrays.stream(blue).sum();

		System.out.println(score);
		System.out.println(res);
	}
	
	private static void print(){
		for(int[] arr : greenBoard)
			System.out.println(Arrays.toString(arr));
		
		System.out.println();
		
		for(int[] arr : blueBoard)
			System.out.println(Arrays.toString(arr));
		
		System.out.println();
	}
	
	private static void set() throws IOException{
		n = Integer.parseInt(br.readLine());
		greenBoard = new int[6][4];
		blueBoard = new int[4][6];
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			Block b = new Block(Integer.parseInt(st.nextToken()),
				                Integer.parseInt(st.nextToken()),
				                Integer.parseInt(st.nextToken()));
			
			moveOnBoard(b, true);
			moveOnBoard(b, false);
			
			//2. 밑에 줄부터 타일로 가득찬 줄이 있는지 확인하고 처리
			removeLine(true);
			removeLine(false);
			
			//3. 연한 칸에 블록이 있는지 처리
			blockInLightColorBoard(true);
			blockInLightColorBoard(false);
			
		}
	}

	private static void moveOnBoard(Block b, boolean isGreen) {
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		
		//1. 블록 놓기
		if(isGreen){
			int r = 0;
			int row = board.length;
			switch(b.t){
				case 1 :
					// 1 x 1
					for(int i=0; i<row; i++){
						if(board[i][b.y] == 1) break;
						r = i;
					}
					board[r][b.y] = 1;
					break;
				case 2 :
					// 1 X 2
					for(int i=0; i<row; i++){
						if(board[i][b.y] == 1 || board[i][b.y+1] == 1) break;
						r = i;
					}
					board[r][b.y] = board[r][b.y+1] = 1;
					break;
				case 3 :
					//2 X 1
					r = 0;
					for(int i=0; i<row-1; i++){
						if(board[i][b.y] == 1 || board[i+1][b.y] == 1) break;
						r = i;
					}
					board[r][b.y] = board[r+1][b.y] = 1;
					break;
			}
		}
		
		
		else{
			int c = 0;
			int col = board[0].length;
			switch (b.t){
				case 1:
					//1 x 1
					for(int i=0; i<col; i++){
						if(board[b.x][i] == 1) break;
						c = i;
					}
					board[b.x][c] = 1;
					break;
				case 2:
					// 1 x 2
					c = 0;
					for(int i=0; i<col-1; i++){
						if(board[b.x][i] == 1 || board[b.x][i+1] == 1) break;
						c = i;
					}
					board[b.x][c] =  board[b.x][c+1] = 1;
					break;
				case 3:
					//2 x 1
					for(int i=0; i<col-1; i++){
						if(board[b.x][i] == 1 || board[b.x+1][i] == 1) break;
						c = i;
					}
					board[b.x][c] = board[b.x+1][c] = 1;
					break;
			}
		}
		
		
	}
	
	/**
	 * @param isGreen
	 * 초록색 보드라면 각 행이 꽉 차있을 때 사라짐
	 * 파란색 보드라면 각 열이 꽉 차있을 때 사라짐
	 */
	private static void removeLine(boolean isGreen){
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		int c = board[0].length;
		int r = board.length;
		
		Queue<int[]> queue = new LinkedList<>();
		if(isGreen){
			for(int i=r-1; i>=0; i--){
				if(Arrays.stream(board[i]).sum() == c) score++;
				else queue.add(Arrays.copyOf(board[i], c));
			}
			
			for(int i=r-1; i>=0; i--){
				if(queue.isEmpty()) Arrays.fill(board[i], 0);
				else board[i] = queue.poll();
			}
		}else{
			int[] temp = new int[r];
			for(int i=c-1; i>=0; i--){
				int sum = 0;
				for(int j=0; j<r; j++){
					sum += board[j][i];
					temp[j] = board[j][i];
				}
				
				if(sum == r) score++;
				else queue.add(Arrays.copyOf(temp, r));
			}
			
			for(int i=c-1; i>=0; i--){
				int[] now = queue.isEmpty() ? null : queue.poll();
				for(int j=0; j<r; j++){
					board[j][i] = (now != null) ? now[j] : 0;
				}
			}
		}
		
	}
	
	private static void blockInLightColorBoard(boolean isGreen){
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		int r = board.length, c = board[0].length;
		int cnt = 0;
		
		if(isGreen){
			for(int i=0; i<2; i++){
				if(Arrays.stream(board[i]).sum() > 0) cnt++;
			}
			
			if(cnt == 0) return;
			
			for(int i=r-1; i>=0; i--){
				if(i >= cnt) board[i] = board[i-cnt];
				else Arrays.fill(board[cnt], 0);
			}
		}else{
			for(int i=0; i<2; i++){
				for(int j=0; j<board.length; j++){
					if(board[j][i] > 0){
						cnt++;
						break;
					}
				}
			}
			
			if(cnt == 0) return;
			
			for(int i=c-1; i>=0; i--){
				for(int j=0; j<r; j++){
					if(i >= cnt) board[j][i] = board[j][i-cnt];
					else board[j][i] = 0;
				}
			}
		}
	}
}


