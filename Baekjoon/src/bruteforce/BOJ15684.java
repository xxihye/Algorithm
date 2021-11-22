package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

    static int N, M, H, min;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] connections;

    public static void main(String[] args) throws IOException {
        input();
        bruteforce(1, 1, 0);
        System.out.println(min > 3 ? -1 : min);
    }

    public static void bruteforce(int startRow, int startCol, int line){
        if(line == 3) return;
        if(line == 0 && game(startRow, startCol, 0)) return;

        for(int i=startRow; i<=H; i++){
            int j = (i == startRow) ? startCol : 1;
            for(; j<N; j++){
                if(connections[i][j]|| connections[i][j-1]) continue;
                else if(j < N-1 && connections[i][j+1]) continue;

                connections[i][j] = true;
                if(!game(i, j, line+1)) bruteforce(i, j, line+1);
                connections[i][j] = false;
            }
        }
    }

    public static boolean game(int r, int c, int line){
        int i = 1;
        while(i <= N) {
            int temp = i;
            for (int j = 1; j <= H; j++) {
                if(temp == N && connections[j][temp-1]) temp--;
                else if (temp != N && connections[j][temp]) temp++;
                else if (temp != 1 && connections[j][temp - 1]) temp--;
            }
            if (temp != i) return false;
            else i++;
        }

        //모든 세로줄이 자기 줄에 도착하는 경우 추가된 가로선이 몇개인지 min에 저장
        min = Math.min(min, line);
        return true;
    }

    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        connections = new boolean[H+1][N];
        min = 4;

        int b, a;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            connections[a][b] = true;
        }
    }
}
