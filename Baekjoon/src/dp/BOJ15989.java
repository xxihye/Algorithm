package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        int[] dp = new int[10001];
        //1로만 만들 수 있으니 1로 초기화
        Arrays.fill(dp, 1);

        //2를 추가해서 만들 수 있는 경우 더하기
        for(int i=2; i<dp.length; i++){
            dp[i] += dp[i-2];
        }

        //3을 추가해서 만들 수 있는 경우 더하기
        for(int i=3; i<dp.length; i++){
            dp[i] += dp[i-3];
        }

        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());

            bw.write(dp[n] + "");

            if(testCase != 0) bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}