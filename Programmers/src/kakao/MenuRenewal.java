package kakao;

import java.util.*;
import java.util.stream.Collectors;

public class MenuRenewal {
	public static void main(String[] args) {
		MenuRenewal mr = new MenuRenewal();
		String[] orders = {"XYZ", "XWY", "WXA"};
		String[] answer = mr.solution(orders, new int[]{2, 3, 4});
		System.out.println(Arrays.toString(answer));
	}
	
	Map<String, Integer> map;
	char[] charArr;
	
	public String[] solution(String[] orders, int[] course) {
		map = new HashMap<>();
		
		List<String> list = new ArrayList<>();
		
		for(int c : course){
			for(String order : orders){
				if(order.length() < c) continue;
				
				charArr = order.toCharArray();
				Arrays.sort(charArr);
				combination("", c, 0);
			}
			
			if(map.isEmpty()) continue;
			final int max = Collections.max(map.values());
			if(max >= 2) list.addAll(map.entrySet().stream().filter(e -> e.getValue() == max).map(Map.Entry::getKey).collect(Collectors.toList()));
			map.clear();
		}
		
		Collections.sort(list);
		
		return list.toArray(String[]::new);
	}
	
	public void combination(String s, int c, int start){
		if(s.length() == c){
			map.put(s, map.getOrDefault(s, 0)+1);
			return;
		}
		
		for(int i=start; i< charArr.length; i++){
			combination(s + charArr[i], c, i+1);
		}
	}
}