package simulation;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ4659 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = "", result = "";

        //자음으로만 이뤄졌거나, 모음 연속 3개, 자음 연속 3개, eo 외 글자들의 연속
        String regex = "^[^aeiou]+$|[aeiou]{3,}|[^aeiou]{3,}|([^eo])\\1+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        while (!(str = br.readLine()).equals("end")) {
            matcher = pattern.matcher(str);
            result = matcher.find() ? "not acceptable." : "acceptable.";
            bw.write("<" + str + "> is " + result);
            bw.newLine();
        }


        bw.flush();
    }


}
