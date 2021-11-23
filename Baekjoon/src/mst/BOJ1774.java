package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1774 {

    static double res;
    static int n, e;
    static Node[] nodes;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        nodes = new Node[n+1];
        parent = new int[n+1];
        res = 0.0;

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            parent[i] = i;
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            Node n1 = nodes[Integer.parseInt(st.nextToken())],
                 n2 = nodes[Integer.parseInt(st.nextToken())];
            union(n1, n2);
        }

        for(int i=1; i<n; i++)
            for(int j=i+1; j<=n; j++)
                pq.add(new Edge(nodes[i], nodes[j], getWeight(nodes[i], nodes[j])));


        kruskal();
        System.out.println(String.format("%.2f", res));

    }

    public static void kruskal(){

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(find(edge.n1) != find(edge.n2)){
                union(edge.n1, edge.n2);
                res += edge.weight;
            }
        }
    }

    public static int find(Node node){
        if(parent[node.no] == node.no) return node.no;
        return find(nodes[parent[node.no]]);
    }

    public static void union(Node n1, Node n2){
        int p1 = find(n1);
        int p2 = find(n2);

        if(p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }


    public static double getWeight(Node n1, Node n2){
        return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
    }
}

class Node{
    int no;
    int x;
    int y;

    public Node(int no, int x, int y) {
        this.no = no;
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge>{
    Node n1;
    Node n2;
    double weight;


    public Edge(Node n1, Node n2, double weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight < o.weight ? -1 : (this.weight == o.weight) ? 0 : 1;
    }
}
