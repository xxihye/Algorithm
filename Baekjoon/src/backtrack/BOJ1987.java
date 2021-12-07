package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ1987 {

    static HashSet<Character> set;
    static int r, c, res = 0;
    static char[][] board;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        r = Integer.parseInt(tmp[0]);
        c = Integer.parseInt(tmp[1]);
        board = new char[r][c];
        set = new HashSet<>();

        for(int i=0; i<r; i++) board[i] = br.readLine().toCharArray();

        set.add(board[0][0]);
        move(1, 0 , 0);
        System.out.println(res);
    }

    public static void move(int depth, int i, int j){
        for(int d=0; d<dir.length; d++){
            int x = i + dir[d][0], y = j + dir[d][1];

            if(x < 0 || y < 0 || x >= r || y >= c ) continue;
            if(set.contains(board[x][y])) continue;

            set.add(board[x][y]);
            move(depth+1, x, y);
            set.remove(board[x][y]);
        }

         res = Math.max(res, depth);
         return;
    }
}
