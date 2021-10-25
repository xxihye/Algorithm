package kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *	코스요리 개수에 맞게 주어진 단품메뉴 조합마다 
 *	코스요리 메뉴 구성 조합을 만들어 map에 저장 (key : 메뉴 조합, value : 해당 조합의 주문 횟수)
 *	map에 저장할 때마다 코스 요리 갯수별 최대 주문 횟수를 m에 저장
 *	메뉴 조합이 끝나면 map에서 주문 횟수가 최대이자 2번이 넘는 경우만 pq에 저장
 *	
 *	코스 배열만큼 조합이 끝나면 pq 사이즈만큼 배열을 생성하여 pq의 모든 요소 저장
 *	-> pq를 통해 배열에 오름차순 정렬하여 저장할 수 있음.
 *
 */
public class MenuRenewal {
	static HashMap<String, Integer> map;
	static int m;

	public String[] solution(String[] orders, int[] course) {
		PriorityQueue<String> pq = new PriorityQueue<>();

		for (int cnt : course) {
			map = new HashMap<String, Integer>();
			m = 0;

			for (String order : orders)
				findMenu(0, "", cnt, 0, order);

			for (String key : map.keySet())
				if (map.get(key) == m && m > 1)
					pq.add(key);
		}

		String ans[] = new String[pq.size()];
		int k = 0;
		while (!pq.isEmpty()) {
			ans[k++] = pq.poll();
		}
		return ans;
	}

	public void findMenu(int depth, String str, int cnt, int start, String order) {
		if (depth == cnt) {
			char[] arr = str.toCharArray();
			Arrays.sort(arr);
			String key = String.valueOf(arr);
			map.put(key, map.getOrDefault(key, 0) + 1);
			m = Math.max(map.get(key), m);
			return;
		}

		for (int i = start; i < order.length(); i++) {
			findMenu(depth + 1, str + order.charAt(i), cnt, i + 1, order);
		}
	}
}
