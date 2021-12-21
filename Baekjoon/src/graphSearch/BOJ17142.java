package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17142 {

    static int n, m, blank, res = 10000;
    static int[][] lab;
    static Vector<Pair> virus, active;
    static boolean[] chk;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][n];
        virus = new Vector<>();
        active = new Vector<>();

        String s;
        for(int i=0; i<n; i++){
            s = br.readLine();
            for(int j=0; j<n; j++){
                lab[i][j] = s.charAt(j*2) - '0';
                if(lab[i][j] == 2) virus.add(new Pair(i, j));
                else if(lab[i][j] == 0) blank++;
            }
        }

        chk = new boolean[virus.size()];

        comb(0, 0);
        System.out.println(res == 10000 ? -1 : res);
        br.close();
    }

    public static void comb(int cnt, int start){
        if(cnt == m){
            bfs();
            return;
        }

        for(int i=start; i<chk.length; i++){
            if(chk[i]) continue;

            chk[i] = true;
            comb(cnt+1, i+1);
            chk[i] = false;
        }
    }

    public static void bfs(){
        boolean[][] visit = new boolean[n][n];

        Queue<Pair> queue= new LinkedList<>();
        for(int i=0; i< chk.length; i++){
            if(!chk[i]) continue;

            Pair v = virus.get(i);
            queue.add(v);
            visit[v.x][v.y] = true;
        }

        int runTime = 0, blankCnt = blank, qSize;
        while(!queue.isEmpty()){

            if(blankCnt == 0) break;
            if(res == 10000 && res < runTime) return;

            //한 턴에 큐에 담긴 만큼의 바이러스만 퍼트리기
            qSize = queue.size();
            while(qSize-- > 0){
                Pair now = queue.poll();

                for(int[] d : dir){
                    int r = now.x + d[0], c = now.y + d[1];

                    if(r < 0 || r >= n || c < 0 || c >= n || visit[r][c] || lab[r][c] == 1) continue;

                    if(lab[r][c] == 0) blankCnt--;
                    visit[r][c] = true;
                    queue.add(new Pair(r, c));
                }
            }
            runTime++;
        }

        if(blankCnt == 0) res = Math.min(runTime, res);
    }
}
class Pair{
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
