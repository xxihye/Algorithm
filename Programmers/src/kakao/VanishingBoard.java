package kakao;

/**
 * https://cano721.tistory.com/75 풀이 참고
 */
public class VanishingBoard {
	
	public static void main(String[] args) {
		VanishingBoard vb = new VanishingBoard();
		int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int[] aloc = {1, 0};
		int[] bloc = {1, 2};
		int answer = vb.solution(board, aloc, bloc);
		System.out.println(answer);
	}
	
	class WL {
		boolean win;
		int cnt;
		
		public WL(boolean win, int cnt) {
			this.win = win;
			this.cnt = cnt;
		}
	}
	
	int r, c;
	static final int MAX = 10_000_000;
	static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public int solution(int[][] board, int[] aloc, int[] bloc) {
		r = board.length;
		c = board[0].length;
		
		WL result = move(true, 0, board, aloc, bloc);
		
		return result.cnt;
	}
	
	public WL move(boolean isATurn, int moveCnt, int[][] board, int[] aloc, int[] bloc) {
		if (loseWhenMyTurn(isATurn, aloc, board) ||
		  loseWhenMyTurn(!isATurn, bloc, board) ){
			return new WL(false, moveCnt);
		}
		
		int win = MAX;
		int lose = 0;
		int x = isATurn ? aloc[0] : bloc[0];
		int y = isATurn ? aloc[1] : bloc[1];
		
		for (int[] d : dir) {
			int nx = x + d[0];
			int ny = y + d[1];
			
			if(!canMoveTo(board, nx, ny)) continue;
			
			board[x][y] = 0; //기존 자리는 발판이 없어짐
			
			WL op;
			
			if(isATurn){
				op = move(false, moveCnt+1, board, new int[] {nx, ny}, bloc);
			}else{
				op = move(true, moveCnt+1, board, aloc, new int[] {nx, ny});
			}
			
			if (!op.win) {
				win = Math.min(win, op.cnt); //상대방이 졌고 나는 이겼음
			} else {
				lose = Math.max(lose, op.cnt); //상대방이 이겼고 나는 졌음
			}
			
			board[x][y] = 1;
		}
		
		if(win == MAX && lose == 0) // //더 움직일 수 없을 때 현재 움직인 횟수 반환
			return new WL(false, moveCnt);
		else if(win != MAX)
			return new WL(true,win); // 이겼을 때 최저값 반환
		else
			return new WL(false,lose); // 졌을 때 최대값 반환
	}
	
	private boolean loseWhenMyTurn(boolean turn, int[] loc, int[][] board) {
		return turn && board[loc[0]][loc[1]] == 0;
	}
	
	private boolean canMoveTo(int[][] board, int x, int y) {
		return (x >= 0 && x < r && y >= 0 && y < c && board[x][y] != 0);
	}
}
	
