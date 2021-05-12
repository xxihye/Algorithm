package string;

import java.util.Scanner;

public class BOJ10808 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] word = sc.nextLine().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		int[] ans = new int[26];
		
		for(int i=0; i<word.length; i++) {
			int idx = word[i] - 'a';
			ans[idx]++;
		}
		
		for(int i : ans)
			sb.append(i + " ");
		
		System.out.println(sb.toString());
	}
}
