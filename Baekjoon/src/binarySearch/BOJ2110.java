package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) 
			arr[i] = Integer.parseInt(br.readLine());
			
		Arrays.sort(arr);
		
		int max = arr[n-1] - arr[0];
		int min = 1;
		int mid;
		
		while(min <= max) {
			mid = (max + min) / 2;
			
			if(getCnt(arr, mid, c)) min = mid + 1;
			else max = mid - 1;
		}
		
		System.out.println(max);
	}
	
	public static boolean getCnt(int[] arr, int dist, int c) {
		int cnt = 1;
		int last = arr[0] + dist;
		
		for(int i=1; i<arr.length; i++) {
			if(arr[i] >= last) {
				last = arr[i] + dist;
				cnt++;
			}
		}
		
		return cnt >= c;
	}

}
