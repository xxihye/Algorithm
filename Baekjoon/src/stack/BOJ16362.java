package stack;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class BOJ16362 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.nextLine().replaceAll(" ", "").toCharArray();
		HashSet<Character> set = new HashSet<>();
		set.add('+');
		set.add('-');
		set.add('*');
		set.add('/');
		set.add('%');
		
		//에러 먼저 잡기 - 괄호
		int[] paren = new int[arr.length];
		Stack<Character> forError = new Stack<>();
		Stack<Integer> forProper = new Stack<>();
		int operand = 0, operator = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == '(') {
				forError.push(arr[i]);
				forProper.push(i);
			}
			else if(set.contains(arr[i])) {
				if(i < arr.length - 1 && set.contains(arr[i+1])) {
					System.out.println("error");
					return;
				}
				operator++;
			}
			else if(arr[i] == ')') {
				if(forError.isEmpty()) {
					System.out.println("error");
					return;
				}else {
					forError.pop();
					paren[forProper.pop()] = i;
				}
			}
			else {
				operand++;
				if(i < arr.length - 1 && arr[i+1] - 'a' >= 0 && arr[i+1] - 'a' < 26) {
					System.out.println("error");
					return;
				}
			}
		}
		
		if(!forError.isEmpty() || operand - operator != 1) {
			System.out.println("error");
			return;
		}
		
		//적절한지 안적절한지
		if(arr[0] == '(' && arr[arr.length-1] == ')') System.out.println("improper");
		
		int cnt = 0;
		for(int i=0; i<paren.length; i++) {
			if(paren[i] != 0) {
				if(paren[i] - i < 4){
					System.out.println("improper");
					return;
				}else cnt+=2;
			}
		}
		
		if(paren.length > 3 && (operand - 2) * 2 > cnt) {
			System.out.println("improper");
		}
		else System.out.println("proper");
		
	}
}
