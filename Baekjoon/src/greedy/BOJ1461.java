package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ1461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()),
                m = Integer.parseInt(st.nextToken());

        Vector<Integer> negative = new Vector<>(),
                        positive = new Vector<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int b = Integer.parseInt(st.nextToken());
            if(b > 0) positive.add(b);
            else negative.add(b);
        }

        negative.sort(null);
        positive.sort(Collections.reverseOrder());

        int res = 0;
        boolean isRight = chkDir(negative, positive); //편도로 갔다올 방향 지정

        if(isRight){
            res += positive.get(0);
            if(positive.size() > m) res += getSteps(positive, m, m, isRight);
            if(!negative.isEmpty()) res += getSteps(negative, m, 0, !isRight);
        }else{
            res += (- negative.get(0));
            if(negative.size() > m) res += getSteps(negative, m, m, isRight);
            if(!positive.isEmpty()) res += getSteps(positive, m, 0, !isRight);
        }

        System.out.println(res);
    }

    public static int getSteps(Vector<Integer> vector, int m, int start, boolean isRight){
        int length = vector.size(), sum = 0;
        while(start < length){
            if(isRight) sum += vector.get(start) * 2;
            else sum += (-vector.get(start)) * 2;
            start += m;
        }
        return sum;
    }

    /**
     * 절대값이 가장 큰 값들을 비교하여 더 큰 쪽으로 편도방향을 정함
     */
    public static boolean chkDir(Vector<Integer> negative, Vector<Integer> positive){
        if(negative.isEmpty()) return true;
        else if(positive.isEmpty()) return false;
        else return Math.abs(negative.get(0)) < Math.abs(positive.get(0));
    }

}
