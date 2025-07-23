package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ25757 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //플레이 신청 횟수 및 게임 종류
        int n = Integer.parseInt(st.nextToken());
        String game = st.nextToken();

        Set<String> set = new HashSet<>();
        while (n-- > 0) {
            set.add(br.readLine());
        }

        int total = set.size();
        int res = 0;
        switch (game) {
            case "Y" -> res = total;
            case "F" -> res = total / 2;
            case "O" -> res = total / 3;
        }

        System.out.println(res);
    }
}


