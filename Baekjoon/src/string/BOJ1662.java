package string;

import java.io.*;
import java.util.*;

public class BOJ1662 {
	static int[] paren = new int[50];
	static char[] s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> st = new Stack<>();
		s = br.readLine().toCharArray();

		for (int i = 0; i < s.length; i++) {
			if (s[i] == '(') st.push(i); //괄호 위치 push
			else if (s[i] == ')') paren[st.pop()] = i; // '('괄호의 쌍의 위치 저장
		}
		System.out.println(traversal(0, s.length));
	}

	static int traversal(int start, int end) {
		int sLength = 0;

		for (int i = start; i < end; i++) {
			if (s[i] == '(') {
				// -1을 하는 이유는 ( 앞의 숫자는 K로 하나의 글자수로 세는 것이 아니기 때문에
				sLength += (s[i - 1] - '0') * traversal(i + 1, paren[i]) - 1; //괄호 중첩일 수도 있어서 재귀로 호출
				i = paren[i]; // 끝나는 괄호 위치로 바꿔서 다음 문자열에 대해 탐색하기 위해
			} else sLength++; //괄호가 아니면 길이 세주기 
		}

		return sLength;
	}

}
