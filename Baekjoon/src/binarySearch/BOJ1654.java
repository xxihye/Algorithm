package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean enable = true;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[] LAN = new int[k];
		for (int i = 0; i < k; i++)
			LAN[i] = Integer.parseInt(br.readLine());

		Arrays.sort(LAN);

		long max = LAN[k - 1]; 
		long min = 1; 
		long middle = 0; 

		while (max >= min) { // 이분탐색 시작
			middle = (max + min) / 2;
			long allCount = 0;

			for (int j = 0; j < LAN.length; j++) 
				allCount += LAN[j] / middle;
			
			if (allCount >= n) min = middle + 1;
			else max = middle - 1;
			
		}
		System.out.println(max);
	}
}
