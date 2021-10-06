package bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ15997 {

	static double[] resProb; //국가별 확률 배열
	static HashMap<String, Integer> nations; //국가이름, 번호
	static int[] score; //국가별 점수 배열
	static Game[] games; //게임별 국가 1,2 확률 w,d,l
	static LinkedList<int[]> tempScore; //점수 순위를 낼 점수, 국가 번호
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 
		resProb = new double[4];
		score = new int[4];
		games = new Game[6];
		nations = new HashMap<>();
		
		for(int i=0; i<4; i++) nations.put(sc.next(), i);
		
		for(int i=0; i<games.length; i++) {
			games[i] = new Game(nations.get(sc.next()),
								nations.get(sc.next()),
								sc.nextDouble(),
								sc.nextDouble(),
								sc.nextDouble());
		}
		
		dfs(0, 1.0);
		
		for(double d : resProb) System.out.println(d);
	}
	
	public static void dfs(int depth, double prob) {
		if(prob == 0) return;
		if(depth == 6) {
			tempScore = new LinkedList<int[]>();
			for(int i=0; i<score.length; i++) 
				tempScore.add(new int[] {score[i], i});
			
			//역순정렬
			Collections.sort(tempScore, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[0] - o1[0];
				}
			});
			
			if(tempScore.get(1)[0] != tempScore.get(2)[0]) {
//				1. 상위 2개 국가
				resProb[tempScore.get(0)[1]] += prob;
				resProb[tempScore.get(1)[1]] += prob;
			}else if(tempScore.get(0)[0] == tempScore.get(1)[0] &&
					tempScore.get(2)[0] == tempScore.get(3)[0] ) {
//				2. 4개 국가 모두 점수가 같을때
				resProb[tempScore.get(0)[1]] += prob/2;
				resProb[tempScore.get(1)[1]] += prob/2;
				resProb[tempScore.get(2)[1]] += prob/2;
				resProb[tempScore.get(3)[1]] += prob/2;
			}else if(tempScore.get(2)[0] == tempScore.get(3)[0]) {
//				3. 최상위 점수는 정해졌지만 두번째 점수가 동일한게 3개 있을경우
				resProb[tempScore.get(0)[1]] += prob;
				resProb[tempScore.get(1)[1]] += prob/3;
				resProb[tempScore.get(2)[1]] += prob/3;
				resProb[tempScore.get(3)[1]] += prob/3;
			}else if(tempScore.get(0)[0] == tempScore.get(1)[0]){
//				4. 최상위 점수가 동일한게 3개 있을 경우
				resProb[tempScore.get(0)[1]] += prob*2 / 3;
				resProb[tempScore.get(1)[1]] += prob*2 / 3;
				resProb[tempScore.get(2)[1]] += prob*2 / 3;
			}else {
//				5. 최상위 점수는 정해졌지만 두번째 점수가 동일한게 2개 있을경우
				resProb[tempScore.get(0)[1]] += prob;
				resProb[tempScore.get(1)[1]] += prob/2;
				resProb[tempScore.get(2)[1]] += prob/2;
			}
			return;
		}
		
		
		
		Game g = games[depth];
		//백 트래킹
		//각 게임당 승자 또는 비긴 사람에게 점수를 주고 dfs 진행 후 점수 취소
		
		//n1이 이겼을 때 
		score[g.n1] += 3;
		dfs(depth+1, prob * g.w);
		score[g.n1] -= 3;
		
		//n1 & n2가 비겼을 때
		score[g.n1] += 1;
		score[g.n2] += 1;
		dfs(depth+1, prob * g.d);
		score[g.n1] -= 1;
		score[g.n2] -= 1;
		
		//n2가 이겼을 때
		score[g.n2] += 3;
		dfs(depth+1, prob * g.l);
		score[g.n2] -= 3;
	}
}


class Game{
	int n1;
	int n2;
	
	double w;
	double d;
	double l;
	
	Game(int n1, int n2, double w, double d, double l) {
		super();
		this.n1 = n1;
		this.n2 = n2;
		this.w = w;
		this.d = d;
		this.l = l;
	}

}