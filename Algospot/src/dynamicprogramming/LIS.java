package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS {
	
	static int n;
	static int[] arr, cache;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(--c >= 0) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			arr = new int[n];
			cache = new int[n+1];
			
			for(int i=0; i<n; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
				cache[i] = -1;
			}
			
			cache[n] = -1;
			
			sb.append(lis(-1)-1 + "\n"); //-1를 하는 이유는 -1부터 시작하도록 했기때문에 해당 1을 빼주는 것
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int lis(int start) {
		if(cache[start+1] != -1) return cache[start+1];
		
		cache[start+1] = 1;
		for(int next = start+1; next < n; ++next) {
			if(start == -1 || arr[start] < arr[next]) cache[start+1] = Math.max(cache[start+1], lis(next)+1);
		}
		
		return cache[start+1];
	}
}
