package kakao;

import java.util.Stack;

public class ClawCraneGame {
	public int solution(int[][] board, int[] moves) {
        Stack<Integer> basket = new Stack<>();
        Stack<Integer>[] boardStack = new Stack[board.length];
        for(int i=0; i<board.length; i++) boardStack[i] = new Stack<Integer>();
        
        for(int j=0; j<board.length; j++)
            for(int i=board.length-1; i>=0; i--)
                if(board[i][j] != 0) 
                    boardStack[j].push(board[i][j]);
        
        int res = 0;
        for(int move : moves){
            if(boardStack[move-1].isEmpty()) continue;
            
            int doll = boardStack[move-1].pop();
            if(!basket.isEmpty() && basket.peek() == doll){
                basket.pop();  
                res += 2;
            }else basket.push(doll);
        }
        return res;
    }
}
