package string;

import java.util.Scanner;

public class BOJ10809 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] word = sc.nextLine().toCharArray();
		
		int[] ans = new int[26];
		
		for(int i=0; i<word.length; i++) {
			int idx = word[i] - 'a';
			if(ans[idx] == 0) ans[idx] = i+1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i : ans) 
			sb.append(i == 0 ? "-1 " : i-1 + " ");
		
		System.out.println(sb.toString());
	}

}
