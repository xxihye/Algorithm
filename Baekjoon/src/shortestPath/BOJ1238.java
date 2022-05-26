package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1238 {
	
	static final int INF = 1000000;
	static int[] dist;
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
		
		@Override
		public String toString() {
			return "Edge{" +
			  "v=" + v +
			  ", w=" + w +
			  '}';
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		dist = new int[N+1];

		List<Edge>[] edges = new ArrayList[N+1]; //도로
		List<Edge>[] reverse = new ArrayList[N+1]; //도로
		for(int i=0; i<=N; i++) {
			edges[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[u].add(new Edge(v, w));
			reverse[v].add(new Edge(u, w));
		}
		
		dijkstra(N, X, edges);
		int[] totalTime = Arrays.copyOf(dist, N+1);
		
		dijkstra(N, X, reverse);
		for(int i=0; i<=N; i++) totalTime[i] += dist[i];
		
		System.out.println(Arrays.stream(totalTime).filter(i -> i < INF).max().getAsInt());
		
	}
	
	public static void dijkstra(int N, int X, List<Edge>[] adj){
		boolean[] chk = new boolean[N+1];
		Arrays.fill(dist, INF);
		dist[X] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(X, dist[X]));
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			
			if(chk[cur.v]) continue;
			
			for(Edge next : adj[cur.v]){
				if(!chk[next.v] && dist[next.v] > dist[cur.v] + next.w){
					dist[next.v] = dist[cur.v] + next.w;
					pq.add(new Edge(next.v, dist[next.v]));
				}
			}
			chk[cur.v] = true;
		}
		
	}
}
