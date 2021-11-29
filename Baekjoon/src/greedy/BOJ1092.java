package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1092 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Integer[] crane = new Integer[n];
        String[] tmp = br.readLine().split(" ");
        for(int i=0; i<n; i++) crane[i] = Integer.parseInt(tmp[i]);

        PriorityQueue<Integer> boxes = new PriorityQueue<>(Collections.reverseOrder());
        int m = Integer.parseInt(br.readLine());
        tmp = br.readLine().split(" ");
        for(int i=0; i<m; i++) boxes.add(Integer.parseInt(tmp[i]));

        Arrays.sort(crane, Collections.reverseOrder());
        if(crane[0] < boxes.peek()) {
            System.out.println("-1");
            return;
        }

        int res = 0;
        while(!boxes.isEmpty()){
            for(int i=0; i<n; i++){
                if(!boxes.isEmpty() && boxes.peek() <= crane[i]) boxes.poll();
            }
            res++;
        }

        System.out.println(res);
    }
}
