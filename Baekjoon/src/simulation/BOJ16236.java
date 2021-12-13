package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16236 {

    static int n, time = 0;
    static Fish shark;
    static int[][] fish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        fish = new int[n][n];

        int feeds = 0;
        String s;
        for (int i = 0; i < n; i++) {
            s = br.readLine();
            for (int j = 0; j < n; j++) {
                fish[i][j] = s.charAt(2 * j) - '0';
                if (fish[i][j] == 9) {
                    shark = new Fish(i, j, 2, 0 , 0);
                    fish[i][j] = 0;
                }else if(fish[i][j] == 1) feeds++;
            }
        }

        if(feeds == 0){
            System.out.println(0);
            return;
        }

        //먹이를 찾는 과정에서 먹을 수 있는 먹이들의 위치를 저장하기 위한 우선순위큐
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        // BFS에 사용할 큐
        Queue<Fish> queue = new LinkedList<>();
        queue.add(shark);

        boolean[][] visit;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (true) {
            visit = new boolean[n][n];
            visit[queue.peek().r][queue.peek().c] = true;

            while(!queue.isEmpty()){
                Fish now = queue.poll();

                for(int d=0; d<dir.length; d++){
                    int r = now.r + dir[d][0], c = now.c + dir[d][1];

                    if(r < 0 || r >= n || c < 0 || c >= n || visit[r][c] || fish[r][c] > now.size) continue;

                    //먹을 수 있는 물고기인 경우
                    if(fish[r][c] != 0 && fish[r][c] < now.size) pq.offer(new Fish(r, c, now.size, now.eatCount+1, now.dist+1)); //실제 먹을 물고기이므로 eatCount+1 하여 저장

                    queue.add(new Fish(r, c, now.size, now.eatCount, now.dist+1)); //먹을 수 있는 물고기를 찾는 과정이므로 eatCount는 증가시키지 않음
                    visit[r][c] = true;
                }
            }

            if(pq.isEmpty()) break;

            Fish f = pq.poll();
            if(f.size == f.eatCount) {
                f.size++;
                f.eatCount = 0;
            }

            fish[f.r][f.c] = 0;
            time += f.dist;
            queue.offer(new Fish(f.r, f.c, f.size, f.eatCount, 0));
            pq.clear();
        }

        System.out.println(time);
    }

    static class Fish implements Comparable<Fish>{
        int r, c, size, eatCount, dist;

        public Fish(int r, int c, int size, int eatCount, int dist) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.eatCount = eatCount;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.dist == o.dist){
                if(this.r == o.r) return this.c - o.c;
                else return this.r - o.r;
            }else return this.dist - o.dist;
        }
    }
}



