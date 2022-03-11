package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
		for(int i=0; i<greenBoard.length; i++)
			for(int j=0; j<greenBoard[0].length; j++)
				res += greenBoard[i][j] + blueBoard[i][j];
				
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
		blueBoard = new int[6][4];
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			Block b = new Block(Integer.parseInt(st.nextToken()),
				                Integer.parseInt(st.nextToken()),
				                Integer.parseInt(st.nextToken()));
			moveOnBoard(b, true);
			moveOnBoard(b, false);
		}
	}

	private static void moveOnBoard(Block b, boolean isGreen) {
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		int row = board.length;
		
		//1. 블록 놓기
		int r = 0;
		if(isGreen){
			if(b.t == 2){
				// 1 x 2
				for(int i=0; i<row; i++){
					if(board[i][b.y] == 1 || board[i][b.y+1] == 1) break;
					r = i;
				}
				board[r][b.y] = board[r][b.y+1] = 1;
			}else if(b.t == 3){
				//2 X 1
				r = 1;
				for(int i=1; i<row-1; i++){
					if(board[i][b.y] == 1 || board[i-1][b.y] == 1) break;
					r = i;
				}
				board[r][b.y] = board[r-1][b.y] = 1;
			}else{
				// 1 x 1
				for(int i=0; i<row; i++){
					if(board[i][b.y] == 1) break;
					r = i;
				}
				board[r][b.y] = 1;
			}
		}else{
			if(b.t == 2){
				// 1 x 2
				r = 1;
				for(int i=1; i<row; i++){
					if(board[i][3 - b.x] == 1 || board[i-1][3 - b.x] == 1) break;
					r = i;
				}
				board[r][3 - b.x] = board[r-1][3 - b.x] = 1;
			}else if(b.t == 3){
				//2 x 1
				for(int i=0; i<row; i++){
					if(board[i][3 - b.x] == 1 || board[i][3 - (b.x+1)] == 1) break;
					r = i;
				}
				board[r][3- b.x] = board[r][3 - (b.x+1)] = 1;
			}else{
				//1 x 1
				for(int i=0; i<row; i++){
					if(board[i][3 - b.x] == 1) break;
					r = i;
				}
				board[r][3 - b.x] = 1;
			}
		}
		
		//2. 밑에 줄부터 타일로 가득찬 줄이 있는지 확인하고 처리
		removeLine(isGreen);
		
		//3. 연한 칸에 블록이 있는지 처리
		blockInLightColorBoard(isGreen);
	}
	
	private static void removeLine(boolean isGreen){
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		int col = board[0].length;
		
		int r = board.length - 1;
		for(int i=board.length-1; i>=0; i--){
			int sum = Arrays.stream(board[r]).sum();
			
			if(sum == col){
				score++;
				for(int j=r; j>0; j--) {
					Arrays.fill(board[j], 0);
					board[j] = Arrays.copyOfRange(board[j-1], 0, col);
				}
				Arrays.fill(board[0], 0);
			}else r--;
		}
	}
	
	private static void blockInLightColorBoard(boolean isGreen){
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		int row = board.length, col = board[0].length;
		
		for(int i=1; i>=0; i--){
			int sum = Arrays.stream(board[1]).sum();
			
			if(sum == 0) break;
			
			for(int j=row-1; j>0; j--) {
				Arrays.fill(board[j], 0);
				board[j] = Arrays.copyOfRange(board[j-1], 0, col);
			}
		}
	}
	
	
}


