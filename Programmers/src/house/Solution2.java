package house;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Solution2 {
	public static void main(String[] args) {
		Solution2 s = new Solution2();
		String res = s.solution("ABCabcA");
		System.out.println(res);
	}
	
	public String solution(String call){
		HashMap<String, Integer> map = new HashMap<>();
		char[] charArr = call.toLowerCase().toCharArray();
		int l = call.length();
		StringBuilder sb;
		
		for(int i=0; i<l; i++){
			sb = new StringBuilder();
			for(int j=i; j<l; j++){
				sb.append(charArr[j]);
				String str = sb.toString();
				map.put(str, map.getOrDefault(str, 0) + 1);
			}
		}
		
		List<Pattern> list = map.entrySet().stream().map(e -> new Pattern(e.getKey(), e.getValue()))
		            .collect(Collectors.toList());
		Collections.sort(list, (o1, o2) -> {
			if(o1.cnt < o2.cnt) return 1;
			else if(o1.cnt == o2.cnt && o1.pattern.length() < o2.pattern.length()) return 1;
			else return -1;
		});
		
		int max = list.get(0).cnt;
		for(Pattern p : list){
			if(p.cnt < max) break;
			call = call.replaceAll("(?i)"+ p.pattern, "");
		}
		
		return call;
	}
	
	class Pattern {
		String pattern;
		int cnt;
		
		public Pattern(String pattern, int cnt) {
			this.pattern = pattern;
			this.cnt = cnt;
		}
	}
	
}
