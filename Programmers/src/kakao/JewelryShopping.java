package kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class JewelryShopping {
	public static void main(String[] args) {
		JewelryShopping js = new JewelryShopping();
		String[] gems = {"A", "A", "A", "A", "A",  "B", "C", "B", "A"};
		int[] res = js.solution(gems);
		System.out.println(Arrays.toString(res));
	}
	
	int[] solution(String[] gems){
		HashSet<String> set = (HashSet<String>) Arrays.stream(gems).collect(Collectors.toSet());
		HashMap<String, Integer> countMap = new HashMap<>();
		
		int goal = set.size();
		set.clear();
		
		int lo = 0, hi = 0;
		int finalLow = 0, finalHi = 0;
		int length = 9999999;
		
		int foundPathUsing = 0;
		
		//슬라이딩 윈도우 lo 전진
		for(; lo < gems.length; lo++){
			boolean foundPath = false;
			
			//슬라이딩 윈도우의 hi 전진
			for(; hi < gems.length && hi>=foundPathUsing;  hi++){
				
				System.out.println("[lo = " + lo + "] hi = " + hi);
				//hi값을 슬라이딩 윈도우에 추가
				if(!countMap.containsKey(gems[hi])){
					countMap.put(gems[hi], 1);
					set.add(gems[hi]);
				}else{
					countMap.put(gems[hi], countMap.get(gems[hi]) + 1);
				}
				
				//현재 슬라이딩 윈도우가 모든 보석을 포함하고 있다면
				if(set.size() == goal){
					//가장 짧은 구간만 저장
					if(Math.abs(hi - lo) < length){
						length = Math.abs(hi - lo);
						finalHi = hi;
						finalLow = lo;
					}
					
					//슬라이딩 윈도우의 상한을 찾았으므로 break
					foundPath = true;
					foundPathUsing = hi;
					
					
					break;
				}
			}
			
			//가능한 슬라이딩 윈도우가 없다면 lo를 증가시켜도 소용없으므로 break
			if(!foundPath) break;
			
			//슬라이딩 윈도우에 lo 제거
			if(countMap.containsKey(gems[lo])){
				int v = countMap.get(gems[lo]);
				if(v == 1){
					countMap.remove(gems[lo]);
					set.remove(gems[lo]);
				}else countMap.put(gems[lo], v-1);
			}
			
			//슬라이딩 윈도우에 현재의 hi 제거 : 다음 hi 반복문에서 추가됨
			if(countMap.containsKey(gems[foundPathUsing])){
				int v = countMap.get(gems[foundPathUsing]);
				
				if(v == 1){
					countMap.remove(gems[foundPathUsing]);
					set.remove(gems[foundPathUsing]);
				}else countMap.put(gems[lo], v-1);
			}
		}
		
		return new int[] {finalLow+1, finalHi+1};
	}
}
