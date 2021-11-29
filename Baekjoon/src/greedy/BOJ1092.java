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

        int m = Integer.parseInt(br.readLine());
        Integer[] boxes = new Integer[m];
        tmp = br.readLine().split(" ");
        for(int i=0; i<m; i++) boxes[i] = Integer.parseInt(tmp[i]);

        Arrays.sort(crane, Collections.reverseOrder());
        Arrays.sort(boxes, Collections.reverseOrder());
        if(crane[0] < boxes[0]) {
            System.out.println("-1");
            return;
        }

        int[] position = new int[n];
        boolean[] checked = new boolean[m];

        int res = 0, count = 0;
        while(true){
            //박스를 다 옮겼으면 종료
            if(count == boxes.length) break;
            //모든 크레인에 대하여 각각 처리
            for(int i=0; i<n; i++){
                //아직 안 옮긴 박스중에서 옮길수 있는 박스를 만날 때까지 반복
                while(position[i] < boxes.length){
                    if(!checked[position[i]] && crane[i] >= boxes[position[i]]){
                        checked[position[i]] = true;
                        position[i]++;
                        count++;
                        break;
                    }
                    position[i]++;
                }
            }
            res++;
        }
        System.out.println(res);
    }
}
