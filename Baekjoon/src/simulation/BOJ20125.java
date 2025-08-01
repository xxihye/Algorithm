package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ20125 {

    /**
     * 왼쪽팔 길이 : 심장 위치 - 왼쪽팔 끝나는 위치
     * 오른쪽팔 길이 : 오른쪽팔 시작 위치  - 심장위치
     * 허리길이 : 허리 끝나는 위치 - 심장위치
     * 왼쪽 다리 : 왼쪽다리 끝 - 허리끝 위치
     * 오른쪽 다리 : 오른쪽다리 끝 - 허리끝 위치
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int heartX = 0, heartY = 0;
        char[][] plate = new char[n][n];

        //쿠키 신체 위치 저장 및 심장 찾기
        for (int i = 0; i < n; i++) {
            plate[i] = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                if (heartX == 0 && heartY == 0 && plate[i][j] == '*') {
                    heartX = i+1;
                    heartY = j;
                    bw.write((heartX + 1) + " " + (heartY + 1) + "\n");
                }
            }
        }

        //팔 시작, 끝 위치 확인
        int start = n, end = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (plate[heartX][j] == '*') {
                end = j;
                start = (start == n) ? j : start;
            }
        }
        bw.write((heartY - end) + " " + (start - heartY) + " ");

        //허리 끝 위치 확인
        start = heartX;
        end = 0;
        for (int i = start + 1; i < n; i++) {
            if (plate[i][heartY] == '*') end = i;
        }
        bw.write((end - start) + " ");

        //왼쪽, 오른쪽 다리 끝 확인
        int left = 0, right = 0;
        for (int i = end + 1; i < n; i++) {
            //왼쪽다리
            if (plate[i][heartY - 1] == '*') left = i;
            if (plate[i][heartY + 1] == '*') right = i;
        }
        bw.write((left - end) + " " + (right - end));

        bw.flush();
        bw.close();
    }
}