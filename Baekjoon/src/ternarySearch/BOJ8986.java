package ternarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ8986 {
	
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		long lo = 1, hi = 1000000000;
		
		while(hi - lo >=3) {
			long p = (2*lo + hi) / 3;
			long q = (lo + 2*hi) / 3;
			
			if(f(p) < f(q)) hi = q;
			else lo = p;
		}
		
		long res = Long.MAX_VALUE;
		for(int i = (int) lo; i<= hi; i++)
			res = Math.min(f(i), res);
		
		System.out.println(res);
	}
	
	public static long f(long x) {
		long res = 0;
		for(int i=1; i<arr.length; i++) 
			res += Math.abs(x * i - arr[i]);
		
		return res;
	}
}
