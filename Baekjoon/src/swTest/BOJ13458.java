package swTest;

import java.io.*;
import java.util.*;

public class BOJ13458 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		double[] arr = new double[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken()),
			c = Integer.parseInt(st.nextToken());
		
		long res = n, q = 0;
		for(int i=0; i<n; i++) {
			if(arr[i] > b) 
				res += (arr[i] - b) % c == 0 ? (arr[i] - b )/ c : (arr[i] - b) / c + 1; //ÃÑ°¨1 + ºÎ°¨n
		}
		
		System.out.println(res);
	}
}
