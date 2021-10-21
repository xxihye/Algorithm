package divideandconquer;

import java.io.*;
import java.util.*;

class BinarySearch {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int k = Integer.parseInt(br.readLine());
		int min = 0, max = n-1, mid;
		
		while(min < max){
			mid = (min + max) / 2; 
			
			if(arr[mid] == k) {
				System.out.println(mid+1);
				return;
			}else if(k < arr[mid]) max = mid-1;
			else min = mid+1;
		}
		
		System.out.println("X");
		
	}
}