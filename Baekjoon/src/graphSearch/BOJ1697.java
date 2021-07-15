package graphSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697 {
	
	static int n, k;
	static int[] check = new int[100001]; // 방문여부 체크와 시간을 동시에..!
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); //수빈이 위치
		k = sc.nextInt(); //동생 위치
		
		if(n == k) {
			System.out.println(0);
		}else 
			System.out.println(bfs());
	}
	
	public static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		check[n] = 1;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			int next = 0;
			for(int i=0; i<3; i++) {
				if(i == 0) next = temp-1;
				else if(i == 1) next = temp + 1;
				else next = temp * 2;
				
				if(next == k) return check[temp];
				
				if(next >= 0 && next < check.length && check[next] == 0) {
					q.add(next);
					check[next] = check[temp] + 1;
				}
			}
		}
		
		return k-n;
	}
}



