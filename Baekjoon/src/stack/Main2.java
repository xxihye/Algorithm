package stack;

import java.util.Scanner;
import java.util.Stack;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<String> stk = new Stack<String>();
		int length = Integer.parseInt(sc.nextLine());
		int count = 0;
		while(count < length) {
			String[] str = sc.nextLine().split("");
			for(String s : str) {
				if(s.equals(")")) {
					if(stk.isEmpty()) {
						stk.push("*");
						stk.push("*");
					}else	
						stk.pop();
				}
				else stk.push(s);
			}
			System.out.println(stk.isEmpty() ? "YES" : "NO");
			stk.clear();
			count++;
		}
	}
}
