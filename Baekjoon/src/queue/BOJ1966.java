package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int c = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		PriorityQueue<Integer> pq;
		Queue<int[]> queue;

		while (--c >= 0) {
			// 입력 받기
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), idx = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			pq = new PriorityQueue<>(Collections.reverseOrder());
			queue = new LinkedList<>();

			for (int i = 0; i < n; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				pq.add(tmp);
				queue.add(new int[] { i, tmp });
			}

			while (!queue.isEmpty()) {
				// pq.peek이랑 queue의 peek이랑 같을 때까지
				// queue.poll()한 것을 queue에 add

				while (pq.peek() != queue.peek()[1]) {
					queue.add(queue.poll());
				}

				// pq.peek이랑 queue의 peek이랑 같다면
				// queue.poll() & pq.poll()
				// queue.poll은 미리 변수에 저장
				// 내가 알고싶은 문서인지 아닌지 확인
				// 알고싶은 문서면 전체 문서의 수 - 지금 queue.size 출력하고 리턴
				int[] document = queue.poll();
				pq.poll();
				if (idx == document[0]) {
					sb.append((n - queue.size()) + "\n");
					break;
				}
			}

		}
		System.out.println(sb.toString());

	}

}
