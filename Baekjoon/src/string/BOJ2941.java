package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2941 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		
		int cnt = 0;
		int length = word.length;
		for(int i=0; i<length; i++){
			if(word[i] == 'j' && i != 0){
				if(word[i-1] == 'l' || word[i-1] == 'n') cnt--;
			}else if(word[i] == '-' ) cnt--;
			else if(word[i] == '='){
				cnt--;
				if(word[i-1] == 'z' && i-2 >= 0 && word[i-2] == 'd') cnt--;
			}
		}
		
		System.out.println(cnt + length);
	}
}
