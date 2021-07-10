package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6588 {
	
	final static String FAIL = "Goldbach's conjecture is wrong";
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] arr = new boolean[1000001];
		for(int i=2; i<arr.length; i++) arr[i] = true;
		
		for(int i=2; (i*i)<=arr.length; i++) {
			if(arr[i]) {
				for(int j=i*i; j<arr.length; j+=i) arr[j] = false;
			} 
		} 
			
		
		int n = Integer.parseInt(br.readLine()); 
		StringBuilder sb = new StringBuilder();
		while(n != 0) {
			boolean chk = false;
			int a = 0;
			for(int i = 3; i<n; i+=2) {
				if(arr[i] && arr[n-i]) {
					a = i;
					break;
				}
			}
			
			if(chk) sb.append(FAIL + "\n");
			else sb.append(n + " = " + a + " + " + (n-a) + "\n" );
			
			n = Integer.parseInt(br.readLine());
		}
		
		System.out.println(sb.toString());
	}
}
