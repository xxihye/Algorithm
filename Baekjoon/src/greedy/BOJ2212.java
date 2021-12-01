package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 최대 k개의 집중국을 세우는 문제 = k개의 영역으로 나누는 것
 * 센서를 오름차순으로 정렬 후 센서들 사이의 거리를 구해서 가장 사이 거리가 먼 연결부터 k-1번 끊어주면 됨
 * ex) 2개의 영역으로 나누기 위해 1개의 연결을 끊음..
 */
public class BOJ2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensors = new int[n];
        int[][] dist = new int[n-1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) sensors[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sensors);

        if(n == 1) {
            System.out.println("0");
            return;
        }

        for(int i=1; i<n; i++) {
            dist[i-1] = new int[] {i-1, sensors[i] - sensors[i-1]};
        }

        Arrays.sort(dist, (o1, o2) -> {
            if(o1[1] < o2[1] || (o1[1] == o2[1] && o1[0] > o2[0])) return 1;
            return -1;
        });

        int res = 0;
        for(int i=k-1; i<n-1; i++) res += dist[i][1];

        System.out.println(res);

//        System.out.println(Arrays.stream(dist).map(o1 -> o1[1]).mapToInt(Integer::intValue).sum());
    }
}
