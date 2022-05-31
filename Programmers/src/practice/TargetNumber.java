package practice;

import java.util.Arrays;

public class TargetNumber {

    static int[] number;
    public static void main(String[] args) {
        TargetNumber targetNumber = new TargetNumber();
        int res = targetNumber.solution(new int[] {1,1,1,1,1} , 3);
        System.out.println(res);
    }

    public int solution(int[] numbers, int target) {
        number = numbers;

        return DFS(-1, target, 0);
    }

    public int DFS(int depth, int target, int total){
        if(depth == number.length - 1){
            if(total == target) return 1;
            else return 0;
        }

        int res = 0;
        res += DFS(depth+1, target, total + number[depth+1]);
        res += DFS(depth+1, target, total - number[depth+1]);

        return res;
    }



}
