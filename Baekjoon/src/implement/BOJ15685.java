package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15685 {
    static String[] dragons = new String[11];
    static boolean[][] board = new boolean[101][101];
    static int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        set();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int x, y, d, g;
        for(int i=0; i<n; i++){
            String[] temp = br.readLine().split(" ");
            x = Integer.parseInt(temp[0]);
            y = Integer.parseInt(temp[1]);
            d = Integer.parseInt(temp[2]);
            g = Integer.parseInt(temp[3]);

            board[y][x] = true;
            String dragon = dragons[g];
            for(int j=0; j<dragon.length(); j++){
                int nowD = (dragon.charAt(j) - '0' + d) % 4;
                x += dir[nowD][0];
                y += dir[nowD][1];
                board[y][x] = true;
            }
        }

        int res = 0;
        for(int i=0; i<100; i++)
            for(int j=0; j<100; j++)
                if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) res++;

        System.out.println(res);
    }

    private static void set() {
        dragons[0] = "0";
        for(int i=1; i<dragons.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=dragons[i-1].length()-1; j>=0; j--)
                sb.append((dragons[i-1].charAt(j) - '0' + 1) % 4);

            dragons[i] = dragons[i-1] + sb.toString();
        }
    }


}


