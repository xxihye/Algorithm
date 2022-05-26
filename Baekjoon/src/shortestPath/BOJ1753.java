package shortestPath;

import java.io.*;
import java.util.*;

public class BOJ1753 {
	
	static final int INF = Integer.MAX_VALUE;
	
	static class Edge {
		int v, w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		LinkedList<Edge>[] edges = new LinkedList[V+1];
		for(int i=0; i<=V; i++)
			edges[i] = new LinkedList<>();
		
		for(int i=0; i<E; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) ;
			int v = Integer.parseInt(st.nextToken()) ;
			int w = Integer.parseInt(st.nextToken());
			edges[u].add(new Edge(v, w));
		}
		
		int[] dist = new int[V+1];
		boolean[] chk = new boolean[V+1];
		Arrays.fill(dist, INF);
		dist[K] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
		pq.add(new Edge(K, 0)); //시작점 k , 가중치 0

		while(!pq.isEmpty()){
			Edge cur =  pq.poll(); //출발점, 가중치
			if(chk[cur.v]) continue;
			
			for(Edge e : edges[cur.v]){
				
				if(!chk[e.v] && dist[e.v] > dist[cur.v] + e.w){
					dist[e.v] = dist[cur.v] + e.w;
					pq.add(new Edge(e.v, dist[e.v]));
				}
			}
			
			chk[cur.v] = true;
		}
		
		
		for(int i=1; i<=V; i++){
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
	}
	
}
