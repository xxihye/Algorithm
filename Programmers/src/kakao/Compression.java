package kakao;

import java.util.*;

public class Compression {
	public static void main(String[] args) {
		Compression c = new Compression();
		c.solution("KAKAO");
		c.solution("TOBEORNOTTOBEORTOBEORNOT");
	}
	
	public int[] solution(String msg) {
		HashMap<String, Integer> dict = new HashMap<>();
		int index = 26;
		for(int i=0; i<index; i++)
			dict.put(Character.toString('A' + i), i+1);
		
		String[] msgArr = msg.split("");
		ArrayList<Integer> answer = new ArrayList<>();
		int length = msg.length();
		int i = 0;
		
		outer:
		while(i < length){
			
			if(i == length - 1) {
				answer.add(dict.get(msgArr[i]));
				break;
			}
			
			String s = "";
			for(int j=i; j<=length; j++){
				if(j == length) {
					answer.add(dict.get(s));
					break outer;
				}
				s += msgArr[j];
				if(!dict.containsKey(s)) {
					answer.add(dict.get(msg.substring(i, j)));
					dict.put(s, ++index);
					i = j;
					break;
				}
			}
		}
		
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}
}
