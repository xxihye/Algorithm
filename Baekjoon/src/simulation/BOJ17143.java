package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17143 {

    static int r, c, m, res = 0;
    static Shark[][] fishbowls;
    static Queue<Shark> sharkQueue;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        fishbowls = new Shark[r+1][c+1];
        sharkQueue = new LinkedList<>();

        int x, y, s, d, z;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()) - 1;
            z = Integer.parseInt(st.nextToken());
            fishbowls[x][y] = new Shark(x, y, s, d, z);
        }

        for(int j=1; j<=c; j++){
            fishingShark(j); //상어 잡기
            searchSharks(); //움직일 상어 찾기
            move(); //상어 움직이기
        }

        System.out.println(res);
    }

    private static void move() {
        while(!sharkQueue.isEmpty()){
            Shark now = sharkQueue.poll();

            for(int i=0; i<now.s; i++){
                now.x += dir[now.d][0];
                now.y += dir[now.d][1];

                if(now.x <= 0 || now.x > r || now.y <= 0 || now.y > c){
                    //기존 위치로 돌리기
                    now.x -= dir[now.d][0];
                    now.y -= dir[now.d][1];
                    now.d = changeDirection(now.d); //방향 반대로 전환시키기
                    now.x += dir[now.d][0];
                    now.y += dir[now.d][1];
                }
            }

            //자리가 비어있는 경우 
            //또는 자리해있는 상어보다 크기가 큰 경우에만 그 자리에 상어를 새로 넣음
            if(fishbowls[now.x][now.y] == null || fishbowls[now.x][now.y].z < now.z)
                fishbowls[now.x][now.y] = now;
        }
    }

    public static int changeDirection(int d){
        return (d % 2 == 0) ? d+1 : d-1;
    }

    private static void searchSharks() {
        //상어를 찾으면서 해당 자리를 비어둠
        for(int i=1; i<=r; i++){
            for(int j=1; j<=c; j++){
                if(fishbowls[i][j] == null) continue;

                sharkQueue.add(fishbowls[i][j]);
                fishbowls[i][j] = null;
            }
        }
    }

    public static void fishingShark(int col){
        int idx = -1;
        for(int i=1; i<=r; i++){
            if(fishbowls[i][col] != null){
                idx = i;
                break;
            }
        }
        if(idx == -1) return;

        res += fishbowls[idx][col].z; //상어 크기 합
        fishbowls[idx][col] = null; //어항에서 없애기
    }

}

class Shark{
    int x, y, s, d, z;

    public Shark(int x, int y, int s, int d, int z) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}
