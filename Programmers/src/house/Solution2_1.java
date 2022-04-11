package house;

import java.util.Arrays;

public class Solution2_1 {
	public static void main(String[] args) {
		Solution2_1 s = new Solution2_1();
		String res = s.solution("ABCabcA");
		System.out.println(res);
	}
	
	private String solution(String call) {
		int[] fre = new int[26];
		char[] arr = call.toLowerCase().toCharArray();
		for(char c : arr)
			fre[c - 'a']++;
		
		int max = Arrays.stream(fre).max().getAsInt();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++){
			if(fre[arr[i] - 'a'] != max)
				sb.append(call.charAt(i)); //원래 문자열
		}
		
		return sb.toString();
	}
	
	
}
