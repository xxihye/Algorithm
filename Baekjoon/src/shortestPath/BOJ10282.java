package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10282 {

    static int n, d, c, cnt, time;
    static Computer[] computers;
    static int[] distance;
    static final int INF = 10000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            cnt = time = 0;

            distance = new int[n+1];
            computers = new Computer[n+1];
            for(int i=1; i<=n; i++){
                computers[i] = new Computer(i);
                distance[i] = i == c ? 0 : INF;
            }

            for(int i=0; i<d; i++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken()),
                    v2 = Integer.parseInt(st.nextToken()),
                     s = Integer.parseInt(st.nextToken());

                computers[v2].adj.add(new Node(v1, s));
            }

            dijkstra();
            System.out.println(cnt + " " + time);
        }
    }

    public static void dijkstra(){
        int num = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(c, 0));
        cnt++;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(Node e : computers[now.num].adj){
                if(distance[e.num] > distance[now.num] + e.s){
                    distance[e.num] = distance[now.num] + e.s;
                    pq.add(e);
                    if(!computers[e.num].visit){
                        computers[e.num].visit = true;
                        cnt++;
                    }
                }
            }
        }
        time = Arrays.stream(distance).filter(m -> m != INF).max().getAsInt();
    }
}

class Computer{
    int num;
    boolean visit = false;
    ArrayList<Node> adj = new ArrayList<>();

    public Computer(int num) {this.num = num;}
}

class Node implements Comparable<Node>{
    int num;
    int s;

    public Node(int num, int s) {
        this.num = num;
        this.s = s;
    }

    @Override
    public int compareTo(Node o) {return Integer.compare(this.s , o.s);}
}