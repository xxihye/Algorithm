package dp;

import java.io.*;
import java.util.*;

public class BOJ9461 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) dp(Integer.parseInt(br.readLine()));
		
		br.close();
	}
	
	public static void dp(int n) {
		long[] lengths = new long[n+1];
		lengths[0] = 0;
	
		if(n > 4) {
			lengths[1] = lengths[2] = lengths[3] = 1;
			lengths[4] = 2;
			
			for(int i=5; i<=n; i++) 
				lengths[i] = lengths[i-1] + lengths[i-5];
			
			System.out.println(lengths[n]);
		}else if(n == 4) System.out.println(2);
		else System.out.println(1);
		
	}

}
