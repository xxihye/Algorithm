package graphSearch;

import java.util.*;
import java.io.*;

public class BOJ1167 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<int[]>[] graph = new ArrayList[n+1];
		boolean[] visited; 
		
		for(int i=1; i<=n; i++) graph[i] = new ArrayList<int[]>();
		
        StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			
			while (st.hasMoreTokens()) {
				int connectedNode = Integer.parseInt(st.nextToken());
				if (connectedNode == -1)
					break;
				int dist = Integer.parseInt(st.nextToken());
				graph[node].add(new int[] {connectedNode, dist});
			}
		}
		br.close();
		
		int s = 1, d_max = 0; 
		for(int i=1; i<=2; i++) {
			visited = new boolean[n+1];
			Queue<int[]> q = new LinkedList<int[]>();
			visited[s] = true;
			q.add(new int[] {s, 0});
			
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				if(d_max < temp[1]) {
					d_max = temp[1];
					s = temp[0];
				}
				
				for(int[] adj : graph[temp[0]]) {
					if(!visited[adj[0]]) {
						visited[adj[0]] = true;
						q.add(new int[] {adj[0], adj[1] + temp[1]});
					}
				}
			}
		}
		
		System.out.println(d_max);
	}
	
}
