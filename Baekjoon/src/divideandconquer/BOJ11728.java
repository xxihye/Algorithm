package divideandconquer;

import java.io.*;
import java.util.*;

public class BOJ11728 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), 
			m = Integer.parseInt(st.nextToken());
		
		int[] arrA = new int[n];
		int[] arrB = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) arrA[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) arrB[i] = Integer.parseInt(st.nextToken());
		
		int a = 0, b = 0;
		while(a < n || b < m) {
			if(a < n && (b == m || arrA[a] <= arrB[b])) bw.write(arrA[a++] + " ");
			else bw.write(arrB[b++] + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
