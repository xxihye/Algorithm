package bruteforce;

import java.util.*;

public class BOJ15683 {

    static int[][] office;
    static int n, m, min = 64;
    static HashMap<Integer, List<Integer>>  watchDir;
    static int cntOfWall, cntOfCCTV;
    static ArrayList<int[]> cctv;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        office = new int[n][m];
        cctv = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                office[i][j] = sc.nextInt();
                if (office[i][j] == 6) cntOfWall++;
                if(office[i][j] > 0 && office[i][j] < 6){
                    cntOfCCTV++;
                    cctv.add(new int[] {i, j});
                }
            }
        }

        //0 : 우, 1: 상, 2 : 좌, 3 : 하
        watchDir = new HashMap<>();
        watchDir.put(1, List.of(0)); // 0, 1, 2, 3
        watchDir.put(2, List.of(0, 2)); // 02, 13
        watchDir.put(3, List.of(0, 1)); //01, 12, 23, 30
        watchDir.put(4, List.of(0, 1, 2)); //012, 123, 230, 301
        watchDir.put(5, List.of(0, 1, 2, 3)); //0123

        bruteforce(0, office, 0);

        System.out.println(min);
    }

    private static void bruteforce(int cnt, int[][] watchArea, int cntOfWatch) {
        if(cnt == cntOfCCTV){
            int total = (n * m) - cntOfWatch - cntOfWall - cntOfCCTV;
            min = Math.min(min, total);
            return;
        }

        int[] nowCCTV = cctv.get(cnt);
        int[][] tmpWatchArea;
        for(int d=0; d<4; d++){

            if(office[nowCCTV[0]][nowCCTV[1]] == 2 && d > 1) continue; //2번 카메라는 2번만 돌면 돼
            else if(office[nowCCTV[0]][nowCCTV[1]] == 5 && d > 0) continue; //5번 카메라는 1번만

            tmpWatchArea = copy(watchArea);

            //방향으로 tmpWatchArea 채우기
            int tmp = watch(tmpWatchArea, d, cnt);
            //print(tmpWatchArea);

            //채운 tmpWatchArea로 다음 단계 진행
            bruteforce(cnt+1, tmpWatchArea, cntOfWatch + tmp);
        }

    }

    public static int[][] copy(int[][] arr){
        int[][] res = new int[n][m];
        for(int i=0; i<n; i++) res[i] = arr[i].clone();
        return res;
    }

    public static void print(int[][] arr){
        for(int[] i : arr)
            System.out.println(Arrays.toString(i));

        System.out.println();
    }

    public static int watch(int[][] tmpWatchArea, int d, int cnt){
        int res = 0;

        int[] nowCCTV = cctv.get(cnt);
        int r = nowCCTV[0], c = nowCCTV[1]; 
        //감시해야될 방향 찾기
        List<Integer> dirList = watchDir.get(office[r][c]);
        for(int tmp : dirList){
            tmp += d;

            switch (tmp % 4){
                case 0 :
                    for(int j=c+1; j<m; j++){
                        if(tmpWatchArea[r][j] == 6) break;
                        else if(tmpWatchArea[r][j] == 0){
                            tmpWatchArea[r][j] = -1;
                            res++;
                        }
                    }
                    break;
                case 1 :
                    for(int i=r-1; i>=0; i--){
                        if(tmpWatchArea[i][c] == 6) break;
                        else if(tmpWatchArea[i][c] == 0){
                            tmpWatchArea[i][c] = -1;
                            res++;
                        }
                    }
                    break;
                case 2 :
                    for(int j=c-1; j>=0; j--){
                        if(tmpWatchArea[r][j] == 6) break;
                        else if(tmpWatchArea[r][j] == 0){
                            tmpWatchArea[r][j] = -1;
                            res++;
                        }
                    }
                    break;
                case 3 :
                    for(int i=r+1; i<n; i++){
                        if(tmpWatchArea[i][c] == 6) break;
                        else if(tmpWatchArea[i][c] == 0){
                            tmpWatchArea[i][c] = -1;
                            res++;
                        }
                    }
                    break;
            }

        }

        return res;
    }
}
