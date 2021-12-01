package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()),
            n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[k];

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0; i<k; i++){
            arr[i] = Long.parseLong(st.nextToken());
            pq.add(arr[i]);
        }

        long min = 0;
        while(n-- > 0){
            min = pq.poll();

            for(long l : arr){
                if((min * l) >= ((long) 2 <<30)) break;

                pq.offer(min * l);

                if(min % l == 0) break;
            }
        }

        System.out.println(min);
    }

}
