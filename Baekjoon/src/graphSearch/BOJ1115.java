package graphSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1115 {
	
	static boolean[] visited;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		arr = new int[n];
		visited = new boolean[n];
		
		int i;
		for(i=0; i<n; i++) arr[i] = sc.nextInt();
		
		int res = 0;
		for(i=0; i<n; i++) {
			if(!visited[i]) {
				dfs(i);
				res++;
			}
		}
		
		System.out.println(res == 1 ? 0 : res); 
		//1개라면 사이클이 1개 -> 완전한 순열과 동일함 -> 0으로 출력
	}
	
	public static void dfs(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		visited[i] = true;
		
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			
			int next = arr[tmp];
			if(!visited[next]) {
				queue.add(next);
				visited[next] = true;
			}
		}
	}
}
