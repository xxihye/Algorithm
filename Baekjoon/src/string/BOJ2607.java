package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2607 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int res = 0;

        //첫번째 단어
        String word = br.readLine();
        int len = word.length();

        String str = "";
        for(int i=1; i<n; i++){
            str = br.readLine();

            int cnt = 0;
            int[] arr = new int[26];

            //단어의 문자 종류별 개수 카운팅
            for(char c : str.toCharArray()){
                arr[(c - 'A')]++;
            }

            //동일한 문자 개수 계산
            for(char c : word.toCharArray()){
                if(arr[c - 'A'] > 0){
                    arr[c - 'A']--;
                    cnt++;
                }
            }

            //1. DOG GOD : 문자 길이가 같고, 동일한 문자개수가 단어길이랑 같을 때
            //2. GOD GOL : 문자 길이가 같고, 동일한 문자의 개수가 단어 길이 - 1
            //2. GOD -> GO, GOOD : 문자 길이 차이가 1, 동일한 문자 개수가 작은 길이와 같을 때

            if(len == str.length() && cnt == len) res++;
            else if (len == str.length() && cnt == len - 1) res++;
            else if (Math.abs(len - str.length()) == 1 && cnt == Math.min(len, str.length())) res++;
        }

        System.out.println(res);
    }
}
