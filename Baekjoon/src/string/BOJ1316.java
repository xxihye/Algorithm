package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1316 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		outer : while(n-- > 0){
			String word = br.readLine();
			boolean[] ck = new boolean[26];
			char old = word.charAt(0);
			ck[old - 97] = true;
			
			for(int i=1; i<word.length(); i++){
				char nw = word.charAt(i);
				if(!ck[nw-97] && nw != old){
					ck[nw-97] = true;
					old = nw;
				}else if(ck[nw - 97] && nw != old){
					continue outer;
				}
			}
			
			cnt++;
		}
		
		System.out.println(cnt);
		br.close();
	}
	
	/**
	 * 내가 작성한 코드
	 * 모든 소문자에 대하여 정규표현식을 만들어서 중복된 문자는 하나만 남겨두도록 함
	 * 정리된 문자열에서 같은 소문자가 연속되지 않은 경우 return false
	 *
	 * 정규표현식과 대체 문자열을 매번 String으로 생성 && 사용되지 않은 문자에 대해서도 반복문이 수행되므로 시간이 오래걸림
	 */
	private static boolean isGroupWord(String word) {
		for(int i=0; i<26; i++){
			char ch = (char)('a' + i);
			String reg = "[" + ch + "]{2,}";
			word = word.replaceAll(reg, ch + "");
		}
		
		for(int i=0; i<word.length(); i++){
			if(word.indexOf(word.charAt(i)) != i) return false;
		}
		return true;
	}
	
}