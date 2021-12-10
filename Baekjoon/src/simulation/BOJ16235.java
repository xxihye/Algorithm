package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16235 {

    static int N, M, K;
    static int[][] ground, food;
    static LinkedList<Tree> trees = new LinkedList<>();
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        food = new int[N+1][N+1];
        ground = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(ground[i], 5);
            for(int j=1; j<=N; j++) food[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            trees.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(trees);

        for(int i=0; i<K; i++)  fourSeasons();

        System.out.println(trees.size());
    }


    public static void fourSeasons(){
        LinkedList<Tree> deadTree = new LinkedList<>();

        int size = trees.size();
        for(int i=0; i<size; i++){
            Tree t = trees.poll();
            if(ground[t.x][t.y] < t.age) deadTree.add(t);
            else {
                ground[t.x][t.y] -= t.age++;
                trees.add(t);
            }
        }

        for(Tree t : deadTree){
            ground[t.x][t.y] += (t.age / 2);
        }

        ArrayList<Tree> parent = new ArrayList<>();
        size = trees.size();
        for(int i=0; i<size; i++){
            Tree t = trees.poll();
            if((t.age) % 5 == 0){
                for(int d=0; d<dir.length; d++){
                    int r = t.x + dir[d][0], c = t.y + dir[d][1];
                    if(r < 1 || r > N || c < 1 || c > N) continue;
                    trees.add(new Tree(r, c, 1));
                }
            }
            parent.add(t);
        }

        trees.addAll(parent);

        for(int i=1; i<=N; i++)
            for (int j=1; j<=N; j++)
                ground[i][j] += food[i][j];
    }
}

class Tree implements Comparable<Tree> {
    int x;
    int y;
    int age;

    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
       return Integer.compare(this.age, o.age);
    }
}
