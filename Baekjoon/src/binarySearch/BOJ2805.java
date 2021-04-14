package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int max = arr[n-1], min = 1;
		int mid;
		
		while(max >= min) {
			mid = (max + min) / 2;
			long getTreeAmt = 0;
			
			for(int i=0; i<n; i++)
				getTreeAmt += (arr[i] - mid >= 0) ? arr[i] - mid : 0 ;
				
			if(getTreeAmt >= m) min = mid + 1;
			else if(getTreeAmt < m) max = mid - 1;
				
		}
		
		System.out.println(max);
	}
}
