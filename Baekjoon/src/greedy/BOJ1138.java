package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1138 {

    /**
     * 자신보다 큰 사람 수만큼 배열의 자리를 비워둔 후 비어진 자리에 들어감
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int ord = Integer.parseInt(st.nextToken());
            int cnt = 0;
            int j =0;

            while(!(cnt == ord && res[j] == 0)){
                if(res[j++] == 0){
                    cnt++;
                }
            }
            res[j] = i+1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : res){
            sb.append(i + " ");
        }

        System.out.println(sb.toString().trim());
    }
}
