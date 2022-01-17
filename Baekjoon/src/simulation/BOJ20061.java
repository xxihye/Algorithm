package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20061 {
	
	static class Block{
		int t, x1, y1, x2, y2;
		
		public Block(int t, int x1, int y1, int x2, int y2) {
			this.t = t;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, score;
	static ArrayDeque<Block> blocks;
	static int[][] greenBoard, blueBoard;
	
	public static void main(String[] args) throws IOException {
		set();
		
		onRedBoard();
		
		int res = 0;
		for(int i=0; i<greenBoard.length; i++)
			for(int j=0; j<greenBoard[0].length; j++)
				res += greenBoard[i][j] + blueBoard[i][j];
				
		System.out.println(score);
		System.out.println(res);
		print();
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
		blocks = new ArrayDeque<>();
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = (t == 1) ? 0 : (t == 2) ? x1 : (x1 + 1);
			int y2 = (t == 1) ? 0 : (t == 2) ? (y1 + 1) : y1;
			blocks.add(new Block(t, x1, y1, x2, y2));
		}
	}
	
	private static void onRedBoard(){
		for(Block b : blocks){
			moveOnBoard(b, true);
			moveOnBoard(b, false);
		}
	}

	private static void moveOnBoard(Block b, boolean isGreen) {
		int[][] board = (isGreen) ? greenBoard : blueBoard;
		int row = board.length, col = board[0].length;
		
		//1. 블록 놓기
		int r = 0;
		if(isGreen){
			if(b.t == 2){
				// 1 x 2
				for(int i=0; i<row; i++){
					if(board[i][b.y1] != 0 || board[i][b.y2] != 0) break;
					r = i;
				}
				board[r][b.y1] = board[r][b.y2] = 1;
				
			}else if(b.t == 3){
				//2 X 1
				for(int i=1; i<row; i++){
					if(board[i][b.y1] != 0 || board[i-1][b.y1] != 0) break;
					r = i;
				}
				board[r][b.y1] = board[r-1][b.y1] = 1;
			}else{
				// 1 x 1
				for(int i=0; i<row; i++){
					if(board[i][b.y1] != 0) break;
					r = i;
				}
				board[r][b.y1] = 1;
			}
		}else{
			if(b.t == 2){
				// 1 x 2
				for(int i=1; i<row; i++){
					if(board[i][3-b.x1] != 0 || board[i-1][3-b.x1] != 0) break;
					r = i;
				}
				board[r][3 - b.x1] = board[r-1][3 - b.x1] = 1;
			}else if(b.t == 3){
				//2 x 1
				for(int i=0; i<row; i++){
					if(board[i][3 - b.x1] != 0 || board[i][3 - b.x2] != 0) break;
					r = i;
				}
				board[r][3 - b.x1] = board[r][3 - b.x2] = 1;
			}else{
				//1 x 1
				for(int i=0; i<row; i++){
					if(board[i][3 - b.x1] != 0) break;
					r = i;
				}
				board[r][3- b.x1] = 1;
			}
		}
		
		//2. 밑에 줄부터 타일로 가득찬 줄이 있는지 확인하고 처리
		//removeLine(isGreen);
		
		//3. 연한 칸에 블록이 있는지 처리
		//blockInLightColorBoard(isGreen);
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
					board[j] = Arrays.copyOfRange(board[j-1], 0, col-1);
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
				board[j] = Arrays.copyOfRange(board[j-1], 0, col-1);
			}
		}
	}
	
	
}


