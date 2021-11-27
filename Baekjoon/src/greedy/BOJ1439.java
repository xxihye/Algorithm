package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        char c = s[0];
        int res = 0, length = 0;
        for(int i=1; i<s.length; i++){
            if(s[i] != c){
                s[i] = c;
                length++;
            }else if(length != 0){
                length = 0;
                res++;
            }
        }

        System.out.println((length != 0) ? res+1 : res);
    }
}
