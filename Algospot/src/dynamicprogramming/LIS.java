package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS {
	
	int n;
	int[] arr, cache;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		LIS l = new LIS();
		int c = Integer.parseInt(br.readLine());
		
		while(--c >= 0) {
			l.set();
			
			sb.append(l.lis(-1)-1 + "\n"); //-1를 하는 이유는 -1부터 시작하도록 했기 때문에 해당 1을 빼주는 것
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	
	public void set() throws IOException {
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		cache = new int[n+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
			cache[i] = -1;
		}
		
		cache[n] = -1;
	}
	
	public int lis(int start) {
		if(cache[start+1] != -1) return cache[start+1];
		
		cache[start+1] = 1;
		for(int next = start+1; next < n; ++next) {
			if(start == -1 || arr[start] < arr[next]) cache[start+1] = Math.max(cache[start+1], lis(next)+1);
		}
		
		return cache[start+1];
	}
}
