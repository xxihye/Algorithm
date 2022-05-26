package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916 {
	
	static final int INF = Integer.MAX_VALUE;
	static class Edge implements Comparable<Edge>{
		int v, w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e){
			return Integer.compare(this.w, e.w);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		List<Edge>[] edges = new ArrayList[V+1];
		for(int i=0; i<=V; i++)
			edges[i] = new ArrayList<>();
		
		for(int i=0; i<E; i++){
			st = new StringTokenizer(br.readLine());
			edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		boolean[] chk = new boolean[V+1];
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, dist[s]));
		
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			
			if(chk[cur.v]) continue;
			
			for(Edge next : edges[cur.v]){
				if(!chk[next.v] && dist[next.v] > dist[cur.v] + next.w){
					dist[next.v] = dist[cur.v] + next.w;
					next.w = dist[next.v];
					pq.add(next);
				}
			}
			
			chk[cur.v] = true;
		}
		
		System.out.println(dist[d]);
	}
}
