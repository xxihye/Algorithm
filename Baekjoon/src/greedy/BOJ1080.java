package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1080 {

    static int n, m, ans = 0;
    static int[][] a, b;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        a = new int[n][m];
        b = new int[n][m];

        input(a);
        input(b);

        for(int i=0; i<=n-3; i++)
            for(int j=0; j<=m-3; j++)
                if(a[i][j] != b[i][j]) reverse(a, i, j);

        if(!isSame()) System.out.println(-1);
        else System.out.println(ans);

        br.close();
    }

    private static void input(int[][] tmp) throws IOException {
        for(int i = 0; i<n ; i++){
            String s = br.readLine();
            for(int j=0; j<m ; j++) tmp[i][j] = s.charAt(j) - '0';
        }
    }

    private static void reverse(int[][] a, int r, int c) {
        ans++;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                a[r+i][c+j] = a[r+i][c+j] == 1 ? 0 : 1;
    }

    private static boolean isSame(){
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if(a[i][j] != b[i][j]) return false;
        return true;
    }
}
