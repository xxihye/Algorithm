package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] problems = new int[n][2];
        StringTokenizer st;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            problems[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(problems, (o1, o2) -> {
            if(o1[0] > o2[0] || (o1[0] == o2[0] && o1[1] > o2[1])) return 1;
            else if (o1[0] == o2[0] && o1[1] == o2[1]) return 0;
            return -1;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = 0;
        for(int[] problem : problems){
            pq.add(problem[1]);
            size++;
            if(size > problem[0]) {
                pq.poll();
                size--;
            }
        }

        System.out.println(pq.stream().mapToLong(Integer::longValue).sum());
    }
}
