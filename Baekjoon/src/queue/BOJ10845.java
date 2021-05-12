package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ10845 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		LinkedList<Integer> q = new LinkedList<Integer>();
		
		while(n-- > 0) {
			String order = sc.next();

			if(order.contains("push")) {
				q.add(sc.nextInt());
			}else if(order.contains("pop")) {
				sb.append(q.isEmpty() ? "-1 \n" : q.poll()+ "\n");
			}else if(order.contains("front")) {
				sb.append(q.isEmpty() ? "-1 \n" : q.getFirst()+ "\n");
			}else if(order.contains("back")) {
				sb.append(q.isEmpty() ? "-1 \n" : q.getLast()+ "\n");
			}else if(order.contains("size")) {
				sb.append(q.size() + "\n");
			}else sb.append(q.isEmpty() ? "1\n" : "0\n");
		}
		
		System.out.println(sb.toString());

	}

}
