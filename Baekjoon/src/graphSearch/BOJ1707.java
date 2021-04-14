package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {

	static final int RED = 1;
	static BipartiteGraph[] graph;
	static int v, e; 
	static boolean chkBipartite;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());

			graph = new BipartiteGraph[v + 1];
			chkBipartite = true; 
			
			for (int i = 1; i <= v; i++)
				graph[i] = new BipartiteGraph(i);

			while (e-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				graph[v1].adjacent.add(graph[v2]);
				graph[v2].adjacent.add(graph[v1]);
			}

			for(int i = 1; i <= v; i++) {
				if (!chkBipartite) break;
				
				if(graph[i].color == 0) bfs(i, RED);
			}
			
			
			System.out.println(chkBipartite ? "YES" : "NO");
		}
	}

	public static void bfs(int startV, int color) {
		Queue<BipartiteGraph> queue = new LinkedList<>();
		
		queue.add(graph[startV]);
		graph[startV].color = color;

		while (!queue.isEmpty() && chkBipartite) {
			BipartiteGraph bg = queue.poll();
			
			for (BipartiteGraph adj : bg.adjacent) {
				if (adj.color == 0) {
					queue.offer(adj);
					adj.color = bg.color * -1;
				}
				
				else if(adj.color + bg.color != 0) {
					chkBipartite = false;
					return;
				}
			}

		}

	}

}

class BipartiteGraph {

	int num;
	int color;
	LinkedList<BipartiteGraph> adjacent;

	public BipartiteGraph() {}

	public BipartiteGraph(int num) {
		this.num = num;
		this.color = 0;
		this.adjacent = new LinkedList<BipartiteGraph>();
	}

}
