package kakao;

import java.util.Arrays;
import java.util.Stack;

public class Friends4Block {
	public static void main(String[] args) {
		Friends4Block fb = new Friends4Block();
		System.out.println(fb.solution(4, 5, new String[] {"CCBDE", "AAADE", "AAABF", "CCBBF"}));
		System.out.println(fb.solution(6, 6, new String[] {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
	}
	
	char[][] arr;
	boolean[][] deleted;
	
	public int solution(int m, int n, String[] board) {
		arr = new char[m][];
		for(int i=0; i<m; i++)
			arr[i] = board[i].toCharArray();
		
		int answer = 0;
		while(true){
			int cnt = game(m, n);
			if(cnt == 0) return answer;
			
			answer += cnt;
			
			fall(m, n);
		}
		
	}
	
	public void fall(int m, int n) {
		char[][] temp = new char[m][n];
		for(char[] t : temp)
			Arrays.fill(t, ' ');
		
		Stack<Character> stack = new Stack<>();
		for(int j=0; j<n; j++){
			for(int i=0; i<m; i++){
				if(!deleted[i][j]) stack.push(arr[i][j]);
			}
			
			int r = m-1;
			while(!stack.isEmpty()){
				temp[r--][j] = stack.pop();
			}
		}
	
		arr = temp;
	}
	
	public int game(int m, int n){
		deleted = new boolean[m][n];
		
		for(int i=0; i<m-1; i++){
			for(int j=0; j<n-1; j++){
				if(arr[i][j] != ' ' && isFourBlock(i, j)){
					deleted[i][j] = deleted[i][j+1] = deleted[i+1][j] = deleted[i+1][j+1] = true;
				}
			}
		}
		
		int cnt = 0;
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(deleted[i][j]) cnt++;
			}
		}
		
		return cnt;
	}
	
	public boolean isFourBlock(int i, int j){
		return (arr[i][j] == arr[i+1][j]) && (arr[i][j] == arr[i][j+1]) && (arr[i][j] == arr[i+1][j+1]);
	}
	
	
}
