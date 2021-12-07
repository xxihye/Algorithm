package backtrack;

import java.util.Scanner;

public class BOJ9663 {

    static int res = 0, n;
    static boolean[][] checked;

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        n = sc.nextInt();
        checked = new boolean[n][n];

        Nqueen(0);

        System.out.println(res);
    }

    public static void Nqueen(int depth){
        if(depth == n){
            res++;
            return;
        }

        OUTER : for(int j=0; j<n; j++){
            if(depth != 0){
                 //퀸을 놓으려는 자리의 같은 열 && 왼쪽대각선 && 오른쪽 대각선에 퀸이 있으면 안됨
                 //놓으려는 자리의 이전 행까지만 체크하면 됨
                boolean isPossible = true;
                int gap;
                for(int i=0; i<depth; i++){
                    //같은 열
                    if(checked[i][j]) continue OUTER;
                    //왼쪽 대각선 & 오른쪽 대각선
                    gap = depth - i;
                    if(j - gap >= 0 && j - gap < n && checked[i][j- gap]) continue OUTER;
                    if(j + gap >= 0 && j + gap < n && checked[i][j + gap]) continue OUTER;
                }
            }

            checked[depth][j] = true;
            Nqueen(depth+1);
            checked[depth][j] = false;
        }
    }
}
