package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int budget = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        if(budget >= sum){
            System.out.println(arr[n-1]);
            return;
        }

        int min = 1,
            max = arr[n-1];

        int res = arr[n-1];

        while (min <= max) {
            int mid = (min + max) / 2; //5만
            int total = 0;

            for (int b : arr) {
                total += Math.min(b, mid);
            }

            if (total <= budget) {
                res = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(res);
    }
}