package graphSearch;

import java.io.*;
import java.util.*;

/**
 * visited가 false인 경우에만 각 나라마다 bfs 진행
 *
 * bfs 내에서 인구이동이 있는 나라들을 따로 모으는 queue 생성 후 bfs 돌리면서 나라를 queue에 넣음 + 인구수 누적합 구하기
 * 만약 queue의 size가 1인 경우 인구이동이 없는 것이므로 return;
 * 있다면 사이즈만큼 누적합을 나눠서 반복문을 통해 해당 나라의 인구수를 다시 저장
 *
 * 모든 나라에 대해서 bfs가 끝났을 때 인구이동이 한번이라도 있었다면 res +1
 * 한번도 안일어났다면 return res
 */
public class BOJ16234 {

    static int[][] population;
    static boolean[][] visited;
    static int N, L, M, res = 0;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static ArrayList<int[]> list;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        population = new int[N][N];
        list = new ArrayList<>();
        q = new LinkedList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) population[i][j] = Integer.parseInt(st.nextToken());
        }

        while(true){
            visited = new boolean[N][N];
            boolean chk = false;
            for(int i=0; i<N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    if (bfs(i, j)) chk = true;
                }
            }
            if(chk) res++;
            else break;
        }

        System.out.println(res);
    }

    public static boolean bfs(int r, int c){
        q.clear();
        list.clear();

        q.add(new int[] {r, c});
        list.add(new int[] {r, c});
        visited[r][c] = true;

        int sum = population[r][c];
        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i=0; i<dir.length; i++){
                int x = now[0] + dir[i][0], y = now[1] + dir[i][1];
                if(x < 0 || x >= N || y < 0 || y >= N || visited[x][y]) continue;

                int gap = Math.abs(population[x][y] - population[now[0]][now[1]]);
                if(gap >= L && gap <= M){
                    q.add(new int[] {x, y});
                    list.add(new int[] {x, y});
                    visited[x][y] = true;
                    sum += population[x][y];
                }
            }
        }

        if(list.size() == 1) return false;

        int tmp = sum / list.size();
        for(int[] p : list)
            population[p[0]][p[1]] = tmp;

        return true;
    }
}

