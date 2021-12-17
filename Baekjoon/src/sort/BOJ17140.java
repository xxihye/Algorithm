package sort;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17140 {

    static int r, c, k;
    static int w = 3, h = 3;
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
//        if(w >= h) calculate_R();
//        else calculate_C();
//        print();

        while(arr.get(r).get(c) != k && res < 100){
            res++;

            if(w >= h) calculate_R();
            else calculate_C();

            print();
        }


        System.out.println(res == 100 ? -1 : res);

    }

    public static void print(){
       for(int i=0; i<arr.size(); i++){
           System.out.println(arr.get(i));
       }
    }


    public static void printMap(HashMap<Integer, Integer> map) {
        for(Map.Entry<Integer, Integer> e: map.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
    }

    public static void calculate_R(){
        Vector<Integer> v;
        int length = -1;

        for(int i=0; i<w; i++){
            for(Integer tmp : arr.get(i)) map.put(tmp, map.getOrDefault(tmp, 0) + 1);

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
        for(int i=0; i<w; i++){
            v = arr.get(i);
            size = v.size();
            if(size == length) continue;

            for(int j=size; j<length; j++) v.add(0);
        }

        h = length;
    }

    public static void calculate_C(){
        Vector[] vectors = new Vector[h];
        int length = -1;
        int value = 0;
        for(int j=0; j<h; j++){
            vectors[j] = new Vector();
            for(int i=0; i<w; i++){
                value = arr.get(i).get(j);
                map.put(value, map.getOrDefault(value, 0) + 1);
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
        w = Math.min(100, length);
        Vector<Integer> v;
        for(int i=0; i<w; i++){
            v = new Vector<>();
            for(int j=0; j<h; j++) {
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
