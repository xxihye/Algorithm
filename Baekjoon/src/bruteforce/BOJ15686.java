package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class BOJ15686 {

    /**
     * 치킨집 주소와 집 주소를 각각 리스트에 저장
     * 치킨집 n개중 m개를 고름.
     * 치킨집 m개와 집 들간의 치킨 거리 최소를 구함
     *
     */

    static int n, m, res;
    static Vector<Place> chicken, home;
    static boolean[] chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        res = Integer.MAX_VALUE;

        chicken = new Vector<>();
        home = new Vector<>();

        for(int i=1; i<=n; i++){
            String s = br.readLine();
            for(int j=0; j<n; j++){
                int p = s.charAt(j*2) - '0';
                if(p == 1) home.add(new Place(i, j+1));
                else if(p == 2) chicken.add(new Place(i, j+1));
            }
        }

        chk = new boolean[chicken.size()];

        comb(0, 0);
        System.out.println(res);
    }

    private static void comb(int cnt, int start){
        if(cnt == m){
            getMinDist();
            return;
        }

        for(int i=start; i<chk.length; i++){
            if(chk[i]) continue;

            chk[i] = true;
            comb(cnt+1, i+1);
            chk[i] = false;
        }
    }

    /**
     * 집은 m개의 치킨집과의 거리를 계산하여 가장 작은 거리를 저장하고
     * 각 집마다 가장 작은 거리를 더하여 도시의 치킨거리의 최소값을 구함
     */
    private static void getMinDist(){
        int sum = 0;
        int min, d;

        for(int i=0; i<home.size(); i++){
            min = 100;
            for(int j=0; j<chicken.size(); j++){
                if(!chk[j]) continue;
                Place h = home.get(i);
                Place c = chicken.get(j);
                d = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
                min = min > d ? d : min;
            }
            sum += min;
        }

        res = res > sum ? sum : res;
    }
}


class Place{
    int x;
    int y;

    public Place(int x, int y) {
        this.x = x;
        this.y = y;
    }
}