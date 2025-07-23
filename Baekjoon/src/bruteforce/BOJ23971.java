package bruteforce;

import java.util.*;

public class BOJ23971 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int h = scanner.nextInt();
        int w = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //앉을 수 있는 행 개수
        int row = (h % (n+1) == 0) ? h / (n+1) : h / (n+1) + 1;

        //앉을 수 있는 열 개수
        int col = (w % (m+1) == 0) ? w / (m+1) : w / (m+1) + 1;

        System.out.println(col * row);
    }
}