package divideandconquer;

import java.io.*;
import java.util.*;

public class BOJ1517 {

	static int n;
	static long cnt;
	static int[] arr, tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmp = new int[n];
		cnt = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

		mergeSort(0, n - 1);

		System.out.println(cnt);

	}

	public static void mergeSort(int p, int r) {

		if(p >= r) return;
		
		int q = (p + r) / 2;
		mergeSort(p, q);
		mergeSort(q + 1, r);
		merge(p, q, r);
	}

	public static void merge(int p, int q, int r) {

		int idx = p, left = p, right = q + 1;

		while (left <= q && right <= r) {
			if (arr[left] <= arr[right]) tmp[idx++] = arr[left++];
			else {
				cnt += (q - left + 1);
				tmp[idx++] = arr[right++];
			}
		}

		while (left <= q)
			tmp[idx++] = arr[left++];

		while (right <= r)
			tmp[idx++] = arr[right++];

		for (int i = p; i <= r; i++)
			arr[i] = tmp[i];

	}

	
}
