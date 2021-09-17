package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QUANTIZE {
	
	static int[] pSqSum, pSum, arr;
	static int n; 
	static final int INF = 987654321;
	static int[][] cache; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st;
		while(--c >= 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			pSqSum = new int[n];
			pSum = new int[n];
			cache = new int[n][n];
			
			st = new StringTokenizer(br.readLine());
			if(s >= n) {
				System.out.println("0");
				continue;
			}
			
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				Arrays.fill(cache[i], -1);
			}
			
			
			initialize();
			
			System.out.println(quantize(0, s));
		}
	}
	
	public static void initialize() {
		Arrays.sort(arr);
		int tmp;
		for(int i=0; i<n; i++) {
			tmp = arr[i] * arr[i];
			pSum[i] = (i == 0 ? arr[i] : pSum[i-1] + arr[i]);
			pSqSum[i] = (i == 0 ? tmp : pSqSum[i-1] + tmp);
		}
	}
	
	/**
	 * 
	 * @param from 묶음을 시작할 배열의 인덱스
	 * @param parts 남은 묶음의 개수
	 * @return 최소 오차 제곱의 합
	 */
	public static int quantize(int from, int parts) {
		if(from == n) return 0; //양자화가 모두 진행됨
		if(parts == 0) return INF; //묶을 수 없음
		
		if(cache[from][parts] != -1) return cache[from][parts];
		
		int res = INF;
		for(int partSize = 1; from + partSize <= n; ++partSize) {
			res = Math.min(res, minError(from, from + partSize - 1) 
					 			+ quantize(from + partSize, parts - 1));
			cache[from][parts] = res;
			
		}

		return res;
	}
	
	
	public static int minError(int lo, int hi) {
		int sum = pSum[hi] - (lo == 0 ? 0 : pSum[lo - 1]);
		int pSum = pSqSum[hi] - (lo == 0 ? 0 : pSqSum[lo - 1]);
		
		int m = Math.round((float)sum / (hi - lo + 1));
		
		int res = pSum - (2 * m * sum) + (m * m * (hi - lo + 1));
		
		return res;
	}
	
	
}