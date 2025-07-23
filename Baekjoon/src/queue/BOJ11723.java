package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11723 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //총 연산의 수
        int m = Integer.parseInt(br.readLine());

        //집합 내 요소 존재 여부
        int[] set = new int[21];

        for (int i = 0; i < m; i++) {
            String s = br.readLine();

            String[] order = s.split(" ");
            String cal = order[0];
            int num = (order.length == 2) ? Integer.parseInt(order[1]) : 0;

            switch (cal) {
                case "add":
                    set[num] = 1;
                    break;
                case "remove":
                    set[num] = 0;
                    break;
                case "check":
                    sb.append(set[num] + "\n");
                    break;
                case "toggle":
                    set[num] = (set[num] == 0) ? 1 : 0;
                    break;
                case "all":
                    Arrays.fill(set, 1);
                    break;
                case "empty":
                    Arrays.fill(set, 0);
            }
        }

        System.out.println(sb.toString());

    }
}
