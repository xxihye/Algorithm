package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17144 {

    static int[][] room;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int r, c, t;
    static Vector<Integer> airPurifier = new Vector<>();
    static Queue<Pair> dust;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        dust = new LinkedList<>();

        room = new int[r][c];
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) airPurifier.add(i);
            }
        }

        for(int i=0; i<t; i++){
            checkDust();
            spread();
            rotate();
        }

        int sum = 0;
        for(int[] arr : room) sum += Arrays.stream(arr).sum();
        System.out.println(sum + 2);
    }

    private static void checkDust() {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++) {
                if(room[i][j] == -1 && room[i][j] == 0) continue;
                dust.add(new Pair(i, j, room[i][j]));
            }
        }
    }

    public static void rotate(){
        int top = airPurifier.get(0);
        int down = airPurifier.get(1);
        int i, j;

        //반시계방향으로 돌리기
        for(i=top-1; i>0; i--) room[i][0] = room[i-1][0];
        for(j=0; j<c-1; j++) room[0][j] = room[0][j+1];
        for(i=0; i<top; i++) room[i][c-1] = room[i+1][c-1];
        for(j=c-1; j>1; j--) room[top][j] = room[top][j-1];
        room[top][1] = 0;

        //시계방향으로 돌리기
        for(i=down+1; i<r-1; i++) room[i][0] = room[i+1][0];
        for(j=0; j<c-1; j++) room[r-1][j] = room[r-1][j+1];
        for(i=r-1; i>down; i--) room[i][c-1] = room[i-1][c-1];
        for(j=c-1; j>1; j--) room[down][j] = room[down][j-1];
        room[down][1] = 0;
    }

    public static void spread(){
        while(!dust.isEmpty()){
            Pair now = dust.poll();
            if(now.w < 5)  continue;

            int amount = now.w / 5;
            for(int[] d : dir){
                int x = now.x + d[0], y = now.y + d[1];
                if(x < 0 || x >= r || y < 0 || y >= c || room[x][y] == -1) continue;
                room[x][y] += amount;
                room[now.x][now.y] -= amount;
            }
        }
        dust.clear();
    }

    static class Pair{
        int x, y, w;

        public Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}

