package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arrN = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) arrN[i] = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		int[] arrM = new int[m];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<m; i++) arrM[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arrN);
		
		StringBuilder sb = new StringBuilder();
		boolean chk;
		for(int i=0; i<m; i++) {
			chk = false;
			int max = n-1, min = 0, mid = 0;
			
			while(max >= min) {
				mid = (max + min) / 2;
				
				if(arrM[i] == arrN[mid]) {
					chk = true;
					break;
				}else if(arrM[i] < arrN[mid]) max = mid - 1;
				else min = mid + 1;
			}
			
			if(chk) sb.append(1 + "\n");
			else sb.append(0 + "\n");
		}
		System.out.println(sb.toString());
	}
	
	
	
}
