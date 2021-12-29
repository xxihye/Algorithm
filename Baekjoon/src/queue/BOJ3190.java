package queue;

import java.util.*;

public class BOJ3190 {
	
	static int[][] dir = {{0, 1},{1, 0},{0, -1},{-1, 0}}; //�����»�
	static Deque<int[]> snake;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] board = new int[n+2][n+2];
		for(int i=0; i<k; i++) board[sc.nextInt()][sc.nextInt()] = 1;
		
		int l = sc.nextInt();
		Queue<Move> moves = new LinkedList<>();
		for(int i=0; i<l; i++) moves.add(new Move(sc.nextInt(), sc.next()));
		
		int d = 0, time = 0;
		snake = new LinkedList<>();
		snake.offer(new int[] {1,1});
		
		while(true) {
			time++;
			
			int[] head = snake.peek();
			int r = head[0] + dir[d][0];
			int c = head[1] + dir[d][1];
			
			if(r == 0 || r == n+1 || c == 0 || c == n+1) break;
			else if(isContain(r, c)) break;
				
			snake.addFirst(new int[] {r,c}); //�Ӹ� �÷��� ����ĭ�� ��ġ��Ŵ
			if(board[r][c] == 1) board[r][c] = 0; //����Ա� -> ���� ���ٿ��� ��
			else snake.removeLast(); //����ĭ �����
			
			if(!moves.isEmpty() && moves.peek().time == time) {
				Move m = moves.poll();
				if(m.d.equals("D")) d = (d + 1) % 4;
				else d= (d + 3) % 4;
			}
		}
		System.out.println(time);
	}
	
	public static boolean isContain(int r, int c) {
		for(int[] b : snake) 
			if(b[0] == r && b[1] == c) 
				return true;
		
		return false;
	}
}

class Move{
	int time;
	String d;
	
	public Move(int time, String d) {
		super();
		this.time = time;
		this.d = d;
	}
}