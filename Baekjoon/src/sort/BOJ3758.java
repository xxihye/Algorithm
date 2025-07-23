package sort;

import java.io.*;
import java.util.*;

public class BOJ3758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 횟수
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //팀 개수, 문제 개수, 팀아이디, 엔트리
            int n = Integer.parseInt(st.nextToken()),
                k = Integer.parseInt(st.nextToken()),
                id = Integer.parseInt(st.nextToken()),
                m = Integer.parseInt(st.nextToken());

            //제출 횟수, 마지막 제출 시간 가중치 (idx : 팀아이디)
            int[] num = new int[n + 1];
            int[] time = new int[n + 1];

            //팀의 문제별 최종 점수 환산(문제 번호, 점수)
            int[][] score = new int[n+1][k+1];

            //로그 개수만큼 데이터 확인
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                //팀 ID i, 문제 번호 j, 획득한 점수 s
                int i = Integer.parseInt(st.nextToken()),
                    j = Integer.parseInt(st.nextToken()),
                    s = Integer.parseInt(st.nextToken());

                score[i][j] = Math.max(score[i][j], s); //해당 팀의 문제별 점수
                num[i]++;                               //제출횟수 카운팅
                time[i] = m;                            //제출시점
            }

            List<int[]> list = new ArrayList<>();
            for(int i=1; i<=n; i++){
                //점수, 제출횟수, 제출시간 가중치 (역, 정, 역)
                list.add(new int[] {i, Arrays.stream(score[i]).sum(), num[i], time[i]});
            }

            Collections.sort(list, (t1, t2) -> {
                if(t1[1] == t2[1]) {
                    if(t1[2] == t2[2]) return Integer.compare(t2[3], t1[3]); //제출시간 가중치가 클수록
                    else return Integer.compare(t1[2], t2[2]); //제출 횟수가 적을 수록
                }else return Integer.compare(t2[1], t1[1]);
            });

            for(int i=0; i<n; i++){
                if(list.get(i)[0] == id) {
                    bw.write(i+1 + "\n");
                    break;
                }
            }
        }

        bw.close();
    }
}