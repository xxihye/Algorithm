package greedy;

import java.util.Scanner;

public class BOJ5585 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int change = 1000 - sc.nextInt();

        int res = 0, coin = 500, i = 0;
        while(coin >= 1){
            while(change >= coin){
                change -= coin;
                res++;
            }
            if(change == 0) break;
            coin = (i++ % 2 == 0) ? (coin/5) : (coin/2);
        }
        System.out.println(res);
    }
}
