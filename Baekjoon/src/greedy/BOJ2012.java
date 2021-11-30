package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] expect = new int[n];
        for(int i=0; i<n; i++) expect[i] = Integer.parseInt(br.readLine());
        Arrays.sort(expect);

        long res = 0;
        for(int i=0; i<n; i++) res += Math.abs((i+1) - expect[i]);

        System.out.println(res);
    }
}
