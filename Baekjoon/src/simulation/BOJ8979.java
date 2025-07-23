package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ8979 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()),
            K = Integer.parseInt(st.nextToken());

        LinkedList<Nation> list = new LinkedList<>();

        int idx, g, s, c;
        Nation nation = null;


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            idx = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (idx == K) {
                nation = new Nation(idx, g, s, c);
                list.add(nation);
            }

            list.add(new Nation(idx, g, s, c));
        }

        Collections.sort(list);

        int grade = 0;
        for (Nation n : list) {
            grade++;
            if(n.g == nation.g && n.s == nation.s && n.c == nation.c){
                System.out.println(grade);
                return;
            }

        }
    }
}

class Nation implements Comparable<Nation> {
    int idx;
    int g;
    int s;
    int c;

    public Nation() {
    }

    public Nation(int idx, int g, int s, int c) {
        this.idx = idx;
        this.g = g;
        this.s = s;
        this.c = c;
    }

    @Override
    public int compareTo(Nation nation) {
        if (this.g == nation.g)
            if (this.s == nation.s) {
                return Integer.compare(nation.c, this.c);
            } else return Integer.compare(nation.s, this.s);

        return Integer.compare(nation.g, this.g);
    }
}
