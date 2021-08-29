package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class LUNCHBOX {

	static int n;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while (--t >= 0) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][2];

			String m = br.readLine(), e = br.readLine();
			for (int i = 0; i < n; ++i) {
				arr[i][0] = m.charAt(2 * i) - '0';
				arr[i][1] = e.charAt(2 * i) - '0';
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[1] - o1[1];
				}
			});
			
			System.out.println(heat());
		}
		br.close();
	}

	public static int heat() {
		int res = 0, beginEat = 0;
		
		for (int i = 0; i < n; ++i) {
			beginEat += arr[i][0];
			res = (res < beginEat + arr[i][1]) ? beginEat + arr[i][1] : res;
		}

		return res;
	}
}

