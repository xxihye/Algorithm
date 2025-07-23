package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                //첫줄 요소는 그냥 넣기
                if (i == 0) {
                    pq.add(Integer.parseInt(st.nextToken()));
                    continue;
                }

                //두번째 줄부터 n번째 큰 요소와 비교하면서 더 큰 수를 넣고 pq중 가장 작은 요소를 제거
                //pq.peek은 n번째 큰 요소로 pq는 항상 n개의 요소만 담겨져있음
                int num = Integer.parseInt(st.nextToken());
                if (num > pq.peek()) {
                    pq.poll();
                    pq.add(num);
                }
            }
        }

        System.out.println(pq.poll());
    }
}

