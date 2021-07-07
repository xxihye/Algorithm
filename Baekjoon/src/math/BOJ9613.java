package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9613 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			long res = 0;
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			
			for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
			for(int i=0; i<n-1; i++) {
				for(int j=i+1; j<n; j++) {
					res += gcd(arr[i], arr[j]);
				}
			}
			sb.append(res + "\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	public static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
	

}
