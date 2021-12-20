package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17140 {

    static int r, c, k;
    static int w = 3, h = 3;  //열, 행
    static Vector<Vector<Integer>> arr;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        arr = new Vector<>();
        for(int i=0; i<w; i++){
            Vector<Integer> v = new Vector<>();
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<h; j++) v.add(Integer.parseInt(st.nextToken()));

            arr.add(v);
        }

        int res = 0;

        while(res < 101){
            if((r < h && c < w ) && arr.get(r).get(c) == k) break;
            res++;
            if(h >= w) calculate_R();
            else calculate_C();
        }

        System.out.println(res == 101 ? -1 : res);
        br.close();
    }


    public static void calculate_R(){
        Vector<Integer> v;
        int length = -1;

        for(int i=0; i<h; i++){
            for(Integer tmp : arr.get(i))
                if(tmp > 0) map.put(tmp, map.getOrDefault(tmp, 0) + 1);

            for(Map.Entry<Integer, Integer> e : map.entrySet()) pq.add(new Pair(e.getKey(), e.getValue()));

            length = Math.max(length, pq.size() * 2);
            v = arr.get(i);
            v.clear();

            int cnt = 0;
            while(!pq.isEmpty() && cnt < 100){
                Pair p = pq.poll();
                v.add(p.num); v.add(p.cnt);
                cnt+=2;
            }

            map.clear();
        }

        int size;
        length = Math.min(100, length);
        for(int i=0; i<h; i++){
            v = arr.get(i);
            size = v.size();
            if(size == length) continue;

            for(int j=size; j<length; j++) v.add(0);
        }

        w = length;
    }

    public static void calculate_C(){
        Vector[] vectors = new Vector[w];
        int length = -1;
        int value = 0;

        for(int j=0; j<w; j++){
            vectors[j] = new Vector();
            for(int i=0; i<h; i++){
                value = arr.get(i).get(j);
                if(value > 0) map.put(value, map.getOrDefault(value, 0) + 1);
            }

            for(Map.Entry<Integer, Integer> e : map.entrySet()) pq.add(new Pair(e.getKey(), e.getValue()));

            length = Math.max(length, pq.size() * 2);

            int cnt = 0;
            while(!pq.isEmpty() && cnt < 100){
                Pair p = pq.poll();
                vectors[j].add(p.num);
                vectors[j].add(p.cnt);
                cnt+=2;
            }
            map.clear();
        }

        arr.clear();
        h = Math.min(100, length);
        Vector<Integer> v;
        for(int i=0; i<h; i++){
            v = new Vector<>();
            for(int j=0; j<w; j++) {
                if(i >= vectors[j].size()) v.add(0);
                else v.add((Integer)vectors[j].get(i));
            }
            arr.add(v);
        }
    }
}

class Pair implements Comparable<Pair> {
    int num, cnt;

    public Pair(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.cnt > o.cnt) return 1;
        else if(this.cnt == o.cnt && this.num > o.num) return 1;
        return -1;
    }
}
