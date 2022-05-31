package kakao;

import java.util.HashMap;


public class OpenChat {

    public static String[] solution(String[] records) {
        HashMap<String, String> map = new HashMap<>();

        int length = 0;
        for(String record : records){
            String[] split = record.split(" ");
            if(split.length > 2) map.put(split[1], split[2]);
            if(split[0].charAt(0) != 'C') length++;
        }

        String[] answer = new String[length];
        int index = 0;
        for(String record : records){
            String[] split = record.split(" ");
            char first = split[0].charAt(0);
    
            switch (first) {
                case 'E' -> answer[index++] = map.get(split[1]) + "님이 들어왔습니다.";
                case 'L' -> answer[index++] = map.get(split[1]) + "님이 나갔습니다.";
                default -> {}
            }
        }

        return answer;
    }

}


