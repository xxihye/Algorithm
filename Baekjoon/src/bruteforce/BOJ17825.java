package bruteforce;

import java.util.Scanner;

public class BOJ17825 {
	
	static int[] dice;
	static int res = -1;
	static int[] board = {0, 0, 2, 0, 4, 0, 6, 0, 8, 0, 10,
						  13, 12, 16, 14, 19, 16, 0, 18, 0, 20,
						  22, 22, 24, 24, 25, 26, 30, 28, 35, 30,
						  28, 32, 27, 34, 26, 36, 0, 38, 0, 40, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dice = new int[10];
		for(int i=0; i<dice.length; i++) dice[i] = sc.nextInt();
		
		dfs(0, "");
		System.out.println(res);
	}
	
	public static void dfs(int depth, String order){
		if(depth == 10){
			res = Math.max(res, game(order));
			return;
		}
		
		for(int i=0; i<4; i++) dfs(depth+1, order + i);
	}
	
	public static int game(String order){
		int[] marker = new int[4]; //현재 말의 위치(인덱스)
		boolean[] chk = new boolean[42];
		
	   	int total = 0;
		for(int i=0; i<10; i++){
			int no = order.charAt(i) - '0', //i번째 턴에서 움직일 말의 번호
				cnt = dice[i], //i번째 턴에서 말이 움직여야하는 칸 수
				next;
			
			if(marker[no] == 41) continue;
			
			next = getNext(marker, no, cnt);
			//이동을 마치는 칸이 도착칸이 아니면서 해당 칸에 다른 말이 있을 때
			if(next < 41 && chk[next]) return -1;
			else if(next > 41) next = 41;
			
			chk[marker[no]] = false;  //현재 위치에 말 지움
			chk[next] = true;         //다음 위치에 말 그리기
			marker[no] = next;        //no번 말의 현재 위치 저장
			total += board[next];     //도착한 위치의 점수 획득
		}
		
		return total;
	}
	
	public static int getNext(int[] marker, int no, int cnt){
		int next = marker[no];
		
		for(int i=0; i<cnt; i++){
			if(i == 0 && (marker[no] == 10 || marker[no] == 20 || marker[no] == 30)) next++;
			else{
				switch (next) {
					case 15, 35:
						next += 10;
						break;
					case 29:
						next += 11;
						break;
					case 40:
						return 41;
					default:
						next += 2;
						break;
				}
			}
		}
		
		return next;
	}
}

