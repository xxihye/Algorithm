package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2437 {

    /**
     * 1 1 2 3 6 7 30
     * 1 ~ 6까지 만들 수 있음
     * 기존 만들 수 있는 무게 (1~6)에 각각 다음 무게 7을 더하면 (1~6) ~ (7~13) : 13까지 만들 수 있음
     * 하지만 다음 요소가 기존에 만들 수 있는 무게보다 2이상 크다면 그만큼 공백이 생기므로 만들 수 없음
     * -> 7은 만들 수 있는 최저무게 6보다 하나 더 크므로 만들 수 있음
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st= new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int ans = 0; //현재 만들 수 있는 최저 무게
        for(int i : arr){
            if(i <= ans + 1) ans += i;
            else break;
        }

        System.out.println(ans + 1);
    }



}
