package queue;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ10866 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		LinkedList<Integer> deque = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();
		
		while(n-- > 0) {
			String order = sc.next();
			
			if(order.contains("push_front")) {
				deque.addFirst(sc.nextInt());
			}else if(order.contains("push_back")) {
				deque.addLast(sc.nextInt());
			}else if(order.contains("pop_front")) {
				sb.append(deque.isEmpty() ? "-1 \n" : deque.poll() + "\n");
			}else if(order.contains("pop_back")) {
				sb.append(deque.isEmpty() ? "-1 \n" : deque.pollLast() + "\n");
			}else if(order.contains("size")) {
				sb.append(deque.size() + "\n");
			}else if(order.contains("empty")) {
				sb.append(deque.isEmpty() ? "1\n" : "0\n");
			}else if(order.contains("front")) {
				sb.append(deque.isEmpty() ? "-1\n" : deque.getFirst() + "\n"); 
			}else if(order.contains("back")) {
				sb.append(deque.isEmpty() ? "-1\n" : deque.getLast() + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
