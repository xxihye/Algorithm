package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BOJ1874 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		Queue<Character> queue = new LinkedList<>();
		
		//n번만큼 반복
		int max = 0;
		for(int i=0; i<n; i++) {
			int tmp = sc.nextInt();
			//넣은 최대 숫자가 tmp보다 작은경우
			//넣은 최대 숫자+1 ~ tmp까지 스택에 그만큼 수를 넣어줘야함
			//넣을 때마다 queue에 '+' 넣기
			while(max < tmp) {
				stack.add(++max);
				queue.add('+');
			}
			
			//stack의 peek가 tmp이면 pop -> queue에 '-' 넣기
			//, 아니면 "NO" 출력하고 return
			if(stack.peek() == tmp) {
				stack.pop();
				queue.add('-');
			}
			else {
				System.out.println("NO");
				return;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(Character c : queue) sb.append(c + "\n");
		
		System.out.println(sb.toString());
		return;
	}
}
