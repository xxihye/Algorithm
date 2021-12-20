package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * (r, c)에 벽을 한번도 부수지 않고 이동하는 최단경로, 한번만 부수고 이동하는 최단 경로를 나눠 계산
 * 따라서 방문 여부를 저장하는 배열도 벽을 부순 경우와 부수지 않은 경우에 대해 모두 저장
 * + 거리를 배열에 저장하지 않고 queue에 넣어서 사용함
 */

public class BOJ2206 {

    static int n, m, res = -1;
    static char[][] map;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visit = new boolean[n][m][2];

        for(int i=0; i<n; i++) map[i] = br.readLine().toCharArray();

        bfs();

        System.out.println(res);
    }

    public static void bfs(){
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 1, 0});
        visit[0][0][0] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int x = now[0], y = now[1], pathCnt = now[2], destroy = now[3];

            if(x == n-1 && y == m-1) {
                res = pathCnt;
                return;
            }

            for(int[] d : dir){
                int r = x + d[0], c = y + d[1];
                if(r < 0 || r >= n || c < 0 || c >= m) continue;

                if(map[r][c] == '0' && !visit[r][c][destroy]){
                    //벽이 아닌 경우, 해당 위치를 방문하지 않았을 때
                    //벽을 부쉈던 경우와 부수지 않은 경우를 따로 체크하므로 방문여부 체크시에도 해당 경우를 나눔
                    queue.add(new int[] {r, c, pathCnt+1, destroy});
                    visit[r][c][destroy] = true;
                }else if(map[r][c] == '1' && destroy == 0 && !visit[r][c][1]){
                    //벽인 경우, 해당 위치에 오기 전 벽을 부순 적이 없어야함 && 벽을 부수고 방문한 적이 없어야함
                    queue.add(new int[] {r, c, pathCnt+1, destroy+1});
                    visit[r][c][1] = true;
                }
            }
        }

    }
}
