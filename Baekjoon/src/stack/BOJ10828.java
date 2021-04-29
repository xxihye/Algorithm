package stack;

import java.util.Scanner;
import java.util.Stack;

public class BOJ10828 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int length = sc.nextInt();

		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < length; i++) {
			String cmnd = sc.next();

			if (cmnd.contains("push")) {
				stk.push(sc.nextInt());
			} else if (cmnd.contains("top")) {
				sb.append(stk.isEmpty() ? -1 + "\n" : stk.peek() + "\n");
			} else if (cmnd.contains("size")) {
				sb.append(stk.size() + "\n");
			} else if (cmnd.contains("pop")) {
				sb.append(stk.isEmpty() ? -1 + "\n" : stk.pop() + "\n"); 
			} else if (cmnd.contains("empty")) {
				sb.append(stk.isEmpty() ? 1 + "\n" : 0 + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
