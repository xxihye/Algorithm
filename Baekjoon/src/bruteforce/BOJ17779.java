package bruteforce;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17779 {

    static int N, res = Integer.MAX_VALUE, total = 0;
    static int[][] people;
    static int[] district;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N][N];
        district = new int[6];

        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)   people[i][j] = Integer.parseInt(st.nextToken());
            total += Arrays.stream(people[i]).sum();
        }

        for(int x=0; x<N-2; x++)
            for(int y=1; y<N-1; y++)
                for(int d1=1; x+d1<N; d1++)
                    for(int d2=1; x+d2<N; d2++)
                        bruteforce(x, y, d1, d2);

        System.out.println(res);
    }


    private static void bruteforce(int x, int y, int d1, int d2) {
        if(x+d1+d2 >= N || y-d1 < 0 || y+d2 >= N) return;

        for(int i=1; i<6; i++) district[i] = 0;

        //1번 구역
        int tmpY = y;
        for(int i=0; i<x+d1; i++){
            if(i >= x) tmpY--;
            for(int j=0; j<=tmpY; j++) district[1] += people[i][j];
        }

        //2번 구역
        tmpY = y+1;
        for(int i=0; i<=x+d2; i++){
            if(i > x) tmpY++;
            for(int j=tmpY; j<N; j++) district[2] += people[i][j];
        }

        //3번 구역
        tmpY = y-d1;
        for(int i=x+d1; i<N; i++){
            for(int j=0; j<tmpY; j++) district[3] += people[i][j];
            if(i < x+d1+d2) tmpY++;
        }

        //4번 구역
        tmpY = y+d2;
        for(int i=x+d2+1; i<N; i++){
            for(int j=tmpY; j<N; j++) district[4] += people[i][j];
            if(i <= x+d1+d2) tmpY--;
        }

        district[5] = total - (district[1] + district[2] + district[3] + district[4]);

        int max = 0, min = Integer.MAX_VALUE;
        for(int i=1; i<district.length; i++){
            max = Math.max(district[i], max);
            min = Math.min(district[i], min);
        }

        res = Math.min(max - min, res);
    }
}
