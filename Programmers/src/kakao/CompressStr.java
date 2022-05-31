package kakao;

public class CompressStr {
    public int solution(String s) {
        int min = s.length();

        for (int i = 1; i <= s.length() / 2; i++)
            min = Math.min(min, compression(s, i));

        return min;
    }

    /**
     * 문자열 압축
     *
     * @param str 입력받은 문자열
     * @param i 문자열을 자를 단위
     * @return 압축된 문자열의 길이
     */
    private int compression(String str, int i) {

        int count = 1;
        StringBuilder compression = new StringBuilder();
        String pattern = "";
        int strLength = str.length();

        //0 <= j < 10 (8 + 2)
        for (int j = 0; j <= strLength + i; j += i) {

            String nowStr; // 전 문자열과 비교할 현재 문자열

            if (j >= strLength) nowStr = ""; // 현재 문자열이 없을 때
            else if (strLength < j + i) nowStr = str.substring(j); //남은 비교문장열의 길이가 i가 안될 때
            else nowStr = str.substring(j, j + i); // 그 외에는 i개씩 글자를 자른다

            // 1. 전 문자열이랑 똑같은지 비교한다. (맨 처음이면 비교 X)
            if (j != 0) {
                if (nowStr.equals(pattern)) count++; // 똑같으면
                else if (count >= 2) { // 다르고 count가 2회 이상이면 압축 가능
                    compression.append(count).append(pattern);
                    count = 1;
                } else compression.append(pattern);  // 압축 불가능하면 그냥 그대로 문자열 이어붙이기
            }

            // 2. i 길이만큼 문자열을 자른다.
            pattern = nowStr;
        }

        return compression.toString().length();
    }
}
