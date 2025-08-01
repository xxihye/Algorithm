package graphSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940 {

    //시계방향
    static int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int destX = 0, destY = 0;

        int[][] map = new int[n][m];
        int[][] res = new int[n][m];
        boolean[][] checked = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    destX = i;
                    destY = j;
                }else if (map[i][j] == 1){
                    res[i][j] = -1;
                }
            }
        }

        //bfs
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{destX, destY});
        checked[destX][destY] = true;
        res[destX][destY] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int[] dir : DIRECTION) {
                int r = now[0] + dir[0];
                int c = now[1] + dir[1];

                if (r >= 0 && r < n && c >= 0 && c < m) {
                    if (map[r][c] == 1 && !checked[r][c]) {
                        res[r][c] = res[now[0]][now[1]] + 1;
                        checked[r][c] = true;
                        queue.add(new int[]{r, c});
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<n; i++){
            bw.append(Arrays.toString(res[i]).replaceAll("[,\\[\\]]", ""));
            if(i != n-1) bw.append("\n");
        }

        bw.flush();
        bw.close();
    }
}
