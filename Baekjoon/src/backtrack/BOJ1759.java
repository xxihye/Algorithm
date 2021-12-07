package backtrack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1759 {

    static int L, C;
    static String[] arr;
    static boolean[] chk;
    static ArrayList<String> list;
    static String[] consonant = {"a", "e", "i", "o", "u"};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();
        arr = sc.nextLine().split(" ");
        chk = new boolean[C];
        list = new ArrayList<>();

        Arrays.sort(arr);
        combination(0, "");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = list.size();
        for(int i=0; i<size; i++){
            if(i != size-1) bw.write(list.get(i) + "\n");
            else bw.write(list.get(i));
        }

        bw.flush();
        bw.close();
    }

    public static void combination(int start, String s){
        if(s.length() == L){
            checkCondition(s);
            return;
        }

        for(int i=start; i<arr.length; i++){
            if(chk[i]) continue;
            chk[i] = true;
            combination( i+1, s + arr[i]);
            chk[i] = false;
        }
    }

    public static void checkCondition(String s){
        int cnt = 0;
        for(int i=0; i<consonant.length; i++)
            if(s.contains(consonant[i]))
                cnt++;

        if(cnt >= 1 && s.length() - cnt >= 2) list.add(s);
    }
}
