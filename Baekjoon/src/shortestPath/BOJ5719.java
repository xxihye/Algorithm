package shortestPath;

import java.io.*;
import java.util.*;

public class BOJ5719 {

    static final int INF = 500001;
    static int n, m;
    static int[] distance;
    static Vector<Edge>[] edges;
    static boolean[][] check;
    static ArrayList<Integer>[] parent;

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()),
                end = Integer.parseInt(st.nextToken());

            input();

            dijkstra(start);
            backTracking(start, end);
            dijkstra(start);

            System.out.println(distance[end] == INF ? -1 : distance[end]);
        }
    }


    /**
     * 최단 경로 테이블을 통해서 최단 경로인 간선 true 처리하기 ( end -> start 순)
     */
    private static void backTracking(int start, int node){
        if(node == start) return;

        for(int n : parent[node]){
            if(!check[n][node]){
                check[n][node] = true;
                backTracking(start, n);
            }
        }
    }

    /**
     * 첫번째 실행 : 최단 경로 테이블 구하기
     * 두번째 실행 : 백트래킹을 통해 true 처리된 간선을 제외한 간선들로 거의 최단경로 테이블 구하기
     */
    private static void dijkstra(int start) {
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));

        while(!queue.isEmpty()){
            Edge now = queue.poll();

            for(Edge e : edges[now.v]) {

                if(!check[now.v][e.v]) {
                    if (distance[e.v] == distance[now.v] + e.s) parent[e.v].add(now.v);
                    else if (distance[e.v] > distance[now.v] + e.s ) {
                        distance[e.v] = distance[now.v] + e.s;
                        parent[e.v].clear();  // 최단 경로를 구성하는 노드가 새로 갱신됨
                        parent[e.v].add(now.v);
                        queue.add(e);
                    }
                }
            }
        }
    }

    public static void input() throws IOException{
        distance = new int[n];
        edges = new Vector[n];
        parent = new ArrayList[n];
        check = new boolean[n][n];

        for(int i=0; i<n; i++){
            edges[i] = new Vector<>();
            parent[i] = new ArrayList<>();
        }

        int u, v, p;

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            edges[u].add(new Edge(v, p));
        }
    }
}

class Edge implements Comparable<Edge>{
    int v;
    int s;

    public Edge(int v, int s) {
        this.v = v;
        this.s = s;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.s, o.s);
    }
}