package kakao;

import java.util.Arrays;
import java.util.Stack;

public class KeepDistance {
	public static void main(String[] args) {
		KeepDistance kp = new KeepDistance();
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
							{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
							{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
							{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
							{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		System.out.println(Arrays.toString(kp.solution(places)));
	}
	
	static final int SIZE = 5, DISTANCE = 2;
	boolean[][] visit;
	static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public int[] solution(String[][] places) {
		char[][] charPlace = new char[SIZE][SIZE];
		int[] answer = new int[5];
		int idx = 0;
		
		for(String[] place : places){
			for(int i=0; i<SIZE; i++){
				charPlace[i] = place[i].toCharArray();
			}
			answer[idx++] = checkDistance(charPlace);
		}
		
		return answer;
	}
	
	private int checkDistance(char[][] charPlace) {
		for(int i=0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++){
				if(charPlace[i][j] == 'P'){
					if(!isKeepDistance(i, j, charPlace))
						return 0;
				}
			}
		}
		return 1;
	}
	
	private boolean isKeepDistance(int i, int j, char[][] charPlace) {
		visit = new boolean[SIZE][SIZE];
		Stack<int[]> stack = new Stack<>();
		visit[i][j] = true;
		stack.add(new int[] {i, j, 0});
		
		while(!stack.isEmpty()){
			int[] now = stack.pop();
			
			int x = now[0];
			int y = now[1];
			int depth = now[2];
			
			for(int[] d : dir){
				int nx = x + d[0];
				int ny = y + d[1];
				
				
				if(nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE || visit[nx][ny])
					continue;
				
				//거리두기가 깨진 상황
				if(depth + 1 <= DISTANCE && charPlace[nx][ny] == 'P'){
					return false;
				}
				
				//맨해튼 거리가 2 이내인 빈자리인 경우
				if(depth + 1 < DISTANCE && charPlace[nx][ny] == 'O'){
					visit[nx][ny] = true;
					stack.add(new int[] {nx, ny, depth+1});
				}
			}
		}
		
		return true;
	}
	
}
