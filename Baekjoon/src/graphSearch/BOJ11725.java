package graphSearch;

import java.io.*;
import java.util.*;

public class BOJ11725 {
	
	static ArrayList<Integer>[] graph;
	static int[] parent;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		parent = new int[n+1];
		check = new boolean[n+1];
		
		for(int i=1; i<=n; i++) graph[i] = new ArrayList<Integer>();
		
		StringTokenizer st;
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph[l].add(r);
			graph[r].add(l);
		}
		
		bfs(1);
		
		for(int i=2; i<=n; i++) bw.write(parent[i] + "\n");
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	public static void bfs(int root) {
		Queue<Integer> q = new LinkedList<>();
		q.add(root);
		check[root] = true;
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(Integer i : graph[tmp]) {
				if(!check[i]) {
					q.add(i);
					check[i] = true;
					parent[i] = tmp;
				}
			}
		}
	}
}
