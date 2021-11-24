package graphSearch;

import java.io.*;
import java.util.*;

public class BOJ2933 {

    static int r, c;
    static char[][] cave;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; //상 좌 우 하
    static boolean change;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        r = Integer.parseInt(tmp[0]);
        c = Integer.parseInt(tmp[1]);

        cave = new char[r][c];
        for(int i=0; i<r; i++) cave[i] = br.readLine().toCharArray();

        int n = Integer.parseInt(br.readLine());
        tmp = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            throwTheStick(r - Integer.parseInt(tmp[i]), (i % 2 == 0));
        }

        print();
        br.close();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++) sb.append(cave[i][j]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void throwTheStick(int h, boolean isLeft){
        int j = 0;
        if(isLeft){
            for(; j<c; j++){
                if(cave[h][j] == 'x') {
                    cave[h][j] = '.';
                    break;
                }
            }
        }else{
            j = c-1;
            for(; j>=0; j--){
                if(cave[h][j] == 'x') {
                    cave[h][j] = '.';
                    break;
                }
            }
        }
        bfs();
    }

    /**
     * cave[i][j]와 상하좌우로 연결된 미네랄 덩어리가 공중에 떠있으면(바닥에 붙은 x와 연결되지 않았으면 )
     * 그 미네랄을 바닥 혹은 다른 x위에 접하도록 내림(한칸씩 내림)
     */
    private static void bfs(){
        LinkedList<int[]> list = new LinkedList<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];

        for(int j=0; j<c; j++){
            if(cave[r-1][j] == 'x'){
                queue.add(new int[] {r-1, j});
                visited[r-1][j] = true;
            }
        }

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            for(int d=0; d<4; d++){
                int x = now[0] + dir[d][0], y = now[1] + dir[d][1];
                if(x >= 0 && x < r && y >= 0 && y < c && cave[x][y] == 'x' && !(visited[x][y])) {
                    queue.add(new int[] {x,y});
                    visited[x][y] = true;
                }
            }
        }

        for(int i=0; i<r; i++)
            for (int j = 0; j < c; j++)
                if (cave[i][j] == 'x' && !visited[i][j])
                    list.add(new int[]{i, j});  //떨어질 클러스터 위치 저장

        if(list.size() != 0) fall(list);
    }

    private static void fall(LinkedList<int[]> list) {
        // 현재 떨어질 클러스터를 모두 지운다.
        for (int[] m : list)
            cave[m[0]][m[1]] = '.';

        int cnt = 0;
        // 현재 떨어질 클러스터가 몇칸이나 내려올 수 있는지 체크한다.
        OUTER:
        for (int i = 1; i < r; ++i) {
            for (int[] m : list) {
                if (m[0] + i >= r || cave[m[0] + i][m[1]] == 'x') {
                    break OUTER;
                }
            }
            cnt = i;
        }

        // 계산된 칸 만큼 이동시킨 클러스터를 새로 그린다.
        for (int[] m : list)
            cave[m[0] + cnt ][m[1]] = 'x';
    }
}
