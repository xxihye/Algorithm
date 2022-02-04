package kakao;

import java.util.*;

public class ArcheryCompetition {
	
	static final int LENGTH = 11;
	int[] apeach, ryan;
	int max = 0;
	ArrayList<int[]> list;
	
	public static void main(String[] args) {
		ArcheryCompetition ac = new ArcheryCompetition();
		int[] res = ac.solution(10, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});
		System.out.println(Arrays.toString(res));
	}
	
	int[] solution(int n, int[] info) {
		apeach = info;
		list = new ArrayList<>();
		ryan = new int[LENGTH];
		
		if(info[0] == n) return new int[] {-1};
		makeWinning(n, 0);
		
		if(list.isEmpty()) return new int[] {-1};
		
		list.sort((o1, o2) -> {
			for(int i=LENGTH-1; i>=0; i--){
				if(o1[i] > o2[i]) return -1;
				else if(o1[i] < o2[i]) return 1;
			}
			return 0;
		});
		
		return list.get(0);
	}
	
	void makeWinning(int left, int idx){
		if(left == 0){
			int[] score = scoreWhenRyanWin();
			if(score == null || score[0] - score[1] < max) return;
			
			int diff = score[0] - score[1];
			if(diff > max){
				max = diff;
				list.clear();
			}
			list.add(Arrays.copyOf(ryan, LENGTH));
			return;
		}
		
		if(idx != LENGTH - 1){
			//1. 해당 점수를 라이언이 더 많이 쏘는 경우 : 어피치가 쏜 화살보다 하나 많게 쏴야함
			if (apeach[idx] + 1 <= left) {
				ryan[idx] = apeach[idx] + 1;
				makeWinning(left - ryan[idx], idx + 1);
			}
			//2. 해당 점수를 어피치가 더 많이 쏘는 경우 : 라이언은 이때 화살을 안쏘는게 유리
			ryan[idx] = 0;
			makeWinning(left,idx + 1);
		}
		else {
			//0점은 남은 화살을 다 쏴야함
			ryan[idx] = left;
			makeWinning(0,idx+1);
			ryan[idx] = 0;
		}
	}
	
	int[] scoreWhenRyanWin() {
		int apeachScore = 0, ryanScore = 0;
		
		for(int i=0; i<LENGTH; i++){
			if(apeach[i] != 0 && apeach[i] >= ryan[i]) apeachScore += (10 - i);
			else if(ryan[i] != 0 && apeach[i] < ryan[i]) ryanScore += (10 - i);
		}
		
		return (ryanScore > apeachScore) ? new int[] { ryanScore , apeachScore} : null;
	}
}
