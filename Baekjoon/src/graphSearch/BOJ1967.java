package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1967 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<int[]>[] tree = new ArrayList[n+1];
		for(int i=1; i<=n; i++) tree[i] = new ArrayList<>();
		
		StringTokenizer st;
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()),
				v2 = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			tree[v1].add(new int[] {v2, e});
			tree[v2].add(new int[] {v1, e});
		}
		
	
		
		int s = 1, max = 0;
		for(int i=0; i<2; i++) {
			boolean[] visited = new boolean[n+1];
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {s, 0});
			visited[s] = true;
			
			while(!q.isEmpty()) {
				int[] tmp = q.poll();
				
				if(max < tmp[1]) {
					s = tmp[0];
					max = tmp[1];
				}
				
				for(int[] adj : tree[tmp[0]]) {
					if(!visited[adj[0]]) {
						visited[adj[0]] = true;
						q.add(new int[] {adj[0], tmp[1] + adj[1]});
					}
				}
			}
		}
		
		System.out.println(max);
	}
}
