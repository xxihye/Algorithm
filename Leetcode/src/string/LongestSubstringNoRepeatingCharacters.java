package string;

import java.util.HashMap;

public class LongestSubstringNoRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<>(); // current index of character
	        for (int j = 0, i = 0; j < n; j++) {
	            if (map.containsKey(s.charAt(j))) {
	            	//i의 역할은 겹칠 경우 이전 substring의 겹치면 
	            	//앞글자는 제외하고 뒷글자로 새로 substring 해주기 위해서임
	                i = Math.max(map.get(s.charAt(j)), i);
	            }
	            ans = Math.max(ans, j - i + 1);
	            map.put(s.charAt(j), j + 1);
	        }
	        return ans;
   }
}
