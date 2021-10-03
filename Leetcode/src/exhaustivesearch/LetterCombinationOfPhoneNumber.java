package exhaustivesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfPhoneNumber {
	Map<String, String> phone = new HashMap<String, String>() {
		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};

	List<String> output = new ArrayList<String>();

	public void backtrack(String combination, String next_digits) {
		// 확인할 숫자가 더 이상 없는 경우
		if (next_digits.length() == 0) {
			// 조합 완료 후 list에 추가
			output.add(combination);
		}
		// 확인할 숫자가 남은 경우
		else {
			// 매핑되는 모든 문자를 반복 
			// 사용가능한 다음 숫자
			String digit = next_digits.substring(0, 1);
			String letters = phone.get(digit);
			for (int i = 0; i < letters.length(); i++) {
				String letter = phone.get(digit).substring(i, i + 1);
				// 현재 문자를 조합에 추가
				// 다음 숫자로 진행
				backtrack(combination + letter, next_digits.substring(1));
			}
		}
	}

	public List<String> letterCombinations(String digits) {
		if (digits.length() != 0)
			backtrack("", digits);
		return output;
	}
}
