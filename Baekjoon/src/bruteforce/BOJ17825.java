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
		
		//중복수열,, i는 움직이는 말의 개수
		for(int i=1; i<=4; i++) dfs(i,  "");
		
		System.out.println(res);
	}
	
	public static void dfs(int arrange, String order){
		if(order.length() == 10){
			res = Math.max(res, game(order));
			return;
		}
		
		for(int i=0; i<arrange; i++) dfs(arrange,  order + i);
	}
	
	public static int game(String order){
		int[] marker = new int[4]; //현재 말의 위치(인덱스)
		boolean[] chk = new boolean[42];
		
	   	int total = 0;
		for(int i=0; i<10; i++){
			int no = order.charAt(i) - '0', //i번째 턴에서 움직일 말의 번호
				cnt = dice[i]; //i번째 턴에서 말이 움직여야하는 칸 수
			
			if(marker[no] == 41) continue; // 이미 도착한 말이므로 다음 말 움직임
			
			int next = getNext(marker, no, cnt);
			
			//이동을 마치는 칸이 도착칸이 아니면서 해당 칸에 다른 말이 있을 때, 조건에 어긋나는 경우이므로 바로 RETURN
			if(next < 41 && chk[next]) return -1;
			else if(next > 41) next = 41; //이동 횟수에 상관하지 않고 도착지점을 넘었다면 도착했다고 간주
			
			chk[marker[no]] = false;  //현재 위치에 말 지움
			chk[next] = true;         //다음 위치에 말 그리기
			marker[no] = next;        //no번 말의 현재 위치 저장
			total += board[next];     //도착한 위치의 점수 획득 - 41 도착지점은 점수가 없음
		}
		
		return total;
	}
	
	public static int getNext(int[] marker, int no, int cnt){
		int next = marker[no]; //현재위치에서부터 시작
		
		for(int i=0; i<cnt; i++){
			if(next+1 == board.length) break; //도착지점이라면 break
			
			//10, 20, 30번째는 파란색 화살표 루트로 가야함
			if(i == 0 && (marker[no] == 10 || marker[no] == 20 || marker[no] == 30)) next++;  
			else{
				switch (next) {
					case 15 :
						next += 10;
						break;
					case 35 :
						next -= 10;
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

