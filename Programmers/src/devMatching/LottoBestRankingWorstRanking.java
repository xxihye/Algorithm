package devMatching;

import java.util.HashSet;

/**
 * 	2021 Dev-Matching : 웹 백엔드 개발
 * 로또의 최고 순위와 최저 순위
 */
public class LottoBestRankingWorstRanking {
	
	public int[] solution(int[] lottos, int[] win_nums) {
        int numOfZero = 0, cnt = 0;
        
        for(int i=0; i<lottos.length; i++){
            if(lottos[i] == 0) numOfZero++;
            else if(isContained(lottos[i], win_nums)) cnt++;
        }
       
       
       //맞은 개수 : cnt 
       //최고 순위 (맞은 개수 + 0까지 다 맞는 경우) : 7 - (cnt + numOfZero) > 6 ? 6 : 7 - (cnt + numOfZero)
       //최저 순위 (맞은 개수만 맞은 경우) : 7 - cnt > 6 ? 6 : 7 - cnt
       
       return new int[] {7 - (cnt + numOfZero) > 6 ? 6 : 7 - (cnt + numOfZero), 7 - cnt > 6 ? 6 : 7 - cnt};
      
    }
    
    public boolean isContained(int num, int[] win_nums){
        for(int i : win_nums)
            if(i == num) 
                return true;
        
        return false;
    }
}
