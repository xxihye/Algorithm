package weekly;

import java.util.Collections;
import java.util.LinkedList;

public class VowelsDictionary {
	public static void main(String[] args) {
		System.out.println(solution("AAAAE"));
	}
    public static int solution(String word) {
    	char[] chr = {'A','E','I','O','U'};
    	
    	int answer = 0;
    	int mul = 1;
    	
    	for(int i=word.length()-1; i>=0; i--) {
    		for(int j=0; j<5; j++) {
    			if(word.charAt(i) == chr[j]) 
    				answer += 1 + (j * mul);
    		}
    		mul = (mul * 5) + 1;
    	}
    	return answer;
    }
    
    
}
