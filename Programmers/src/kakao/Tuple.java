package kakao;

import java.util.*;

public class Tuple {
	
	public static void main(String[] args) {
		Tuple t = new Tuple();
		int[] res = t.solution("{{20,111},{111}}");
		System.out.println(Arrays.toString(res));
	}
	
	public int[] solution(String s) {
		String[] arr = s.substring(1, s.length()-2).split("},");
		Arrays.sort(arr, Comparator.comparingInt(String::length));
		
		HashSet<String> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();
		for(String str : arr){
			String[] temp = str.substring(1).split(",");
			
			for(String e : temp){
				if(!set.contains(e)) {
					set.add(e);
					list.add(Integer.parseInt(e));
				}
			}
		}
		
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

}
