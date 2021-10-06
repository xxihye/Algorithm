package weekly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RecommandJob {

	public String solution(String[] table, String[] languages, int[] preference) {
		HashMap<String, HashMap<String, Integer>> map = new HashMap<>();

		String answer = "SI";
		int max = -1;
		for (String s : table) {
			String[] tmp = s.split(" ");
			List<String> list = Arrays.asList(tmp);

			int sum = 0;
			for (int i = 0; i < languages.length; i++) {
				int idx = list.indexOf(languages[i]);
				if (idx > 0) sum += (6 - idx) * preference[i];
			}

			if (sum > max) {
				max = sum;
				answer = tmp[0];
			} else if (sum == max && answer.compareTo(tmp[0]) > 0)
				answer = tmp[0];
		}

		return answer;
	}
}
