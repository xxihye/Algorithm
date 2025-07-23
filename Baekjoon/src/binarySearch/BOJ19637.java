package binarySearch;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ19637 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //칭호 개수와 캐릭터 개수
        int n = Integer.parseInt(st.nextToken()),
            m = Integer.parseInt(st.nextToken());

        //각 칭호와 전투력 배열
        String[] title = new String[n];
        int[] titlePower = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            title[i] = st.nextToken();
            titlePower[i] = Integer.parseInt(st.nextToken());
        }

        //캐릭터별 칭호 탐색
        while (m-- > 0) {
            int num = Integer.parseInt(br.readLine());
            int start = 0;
            int last = n - 1;
            int mid = 0;

            while (start <= last) {
                mid = (last + start) / 2;

                if (titlePower[mid] < num) {
                    start = mid + 1;
                } else {
                    last = mid - 1;
                }
            }
            bw.write(title[start] + "\n");
        }
        bw.flush();
        bw.close();
    }
}