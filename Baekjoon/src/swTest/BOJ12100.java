package swTest;

import java.io.*;
import java.util.*;

public class BOJ12100 {
	
	static int n;
	static int max = 0;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		int[][] board = new int[n][n];
		for(int i=0; i<n; i++) 
			for(int j=0; j<n; j++) 
				board[i][j] = sc.nextInt();

		for(int i=0; i<4; i++) bruteforce(i, 0, board.clone());
		
		System.out.println(max);
	}
	
	public static void bruteforce(int d, int depth, int[][] board) {
		if(depth == 5) return;
		
		Queue<Integer> q = new LinkedList<>();
		int idx;
		switch(d) {
			case 0 :
				//위로 밀기
				for(int j= 0; j<n; j++) {
					idx = 0;
					for(int i=0; i<n; i++) 
						if(board[i][j] > 0) q.offer(board[i][j]);
						
					while(!q.isEmpty()) {
						int now = q.poll();
						if(!q.isEmpty() && q.peek() == now) now += q.poll();
						max = Math.max(now, max);
						board[idx++][j] = now;
					}
				}
				break;
			case 1 : 
				//밑으로 밀기
				for(int j = 0; j<n; j++) {
					idx = n-1;
					for(int i=n-1; i>=0; i--) 
						if(board[i][j] > 0) q.offer(board[i][j]);
					
					while(!q.isEmpty()) {
						int now = q.poll();
						if(!q.isEmpty() && q.peek() == now) now += q.poll();
						max = Math.max(now, max);
						board[idx--][j] = now;
					}
				}
				break;
			case 2 : 
				//왼족으로 밀기
				for(int i= 0; i<n; i++) {
					idx = 0;
					for(int j=0; j<n; j++) 
						if(board[i][j] > 0) q.offer(board[i][j]);
					
					while(!q.isEmpty()) {
						int now = q.poll();
						if(!q.isEmpty() && q.peek() == now) now += q.poll();
						max = Math.max(now, max);
						board[i][idx++] = now;
					}
				}
				
				break;
			case 3 : 
				//오른쪽으로 밀기
				for(int i = 0; i<n; i++) {
					idx = n-1;
					for(int j=n-1; j>=0; j--) 
						if(board[i][j] > 0) q.offer(board[i][j]);
						
					while(!q.isEmpty()) {
						int now = q.poll();
						if(!q.isEmpty() && q.peek() == now) now += q.poll();
						max = Math.max(now, max);
						board[i][idx--] = now;
					}
				}
				break;
		}
		
		for(int i=0; i<4; i++) bruteforce(i, depth+1, board.clone());
	}
}
