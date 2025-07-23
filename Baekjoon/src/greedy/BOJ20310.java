package greedy;

import java.util.Scanner;

public class BOJ20310 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        StringBuilder sb = new StringBuilder();

        //S'의 0과 1의 개수
        int cntZero = (int) s.chars().filter(c -> (char)c == '0').count() / 2;
        int cntOne = (s.length() / 2) - cntZero;

        /**
         * 00110000 -> 0010
         * 1은 앞에서 n/2개를 제외.. 그럼 (n/2)+1번째부터 그냥 쓰는 것
         * 0은 뒤에서 n/2개를 제외.. 그럼 1 ~ (n/2)번째까지는 그냥 쓰는 것
         */

        int idxOne = 0,
            idxZero = 0;

        for(char c : s.toCharArray()){
            if(c == '0'){
                idxZero++;
                if(idxZero <= cntZero) sb.append(c);
            }else {
                idxOne++;
                if(idxOne > cntOne) sb.append(c);
            }
        }

        System.out.println(sb.toString());

    }
}
