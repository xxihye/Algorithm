package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2798 {
	
	static int N, M;
	static int[] arr;
	static int max = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i+2<N; i++) bruteforce(i, 0, 1);
		
		System.out.println(max);
	}
	
	
	public static void bruteforce(int idx, int total, int depth) {
		if(depth == 3) {
			total += arr[idx];
			if(total <= M) max = Math.max(total, max);
			return;
		}
		
		if(total > M) return;
		
		for(int i=idx+1; i<N; i++) {
			bruteforce(i, total + arr[idx], depth+1);
		}
	}
		
}
