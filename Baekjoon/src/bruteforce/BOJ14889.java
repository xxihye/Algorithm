package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
	
	static int n, min = 1000;
	static int[][] stat;
	static boolean[] select;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		stat = new int[n][n];
		select = new boolean[n];
		
		StringTokenizer st;
		for(int i=0; i<n; i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) 
				stat[i][j] = Integer.parseInt(st.nextToken());
		}
		
		bruteforce(0, 0);
		
		System.out.println(min);
	}
	
	
	public static void bruteforce(int start, int cnt) {
		if(cnt == (n/2)) {
			int sum1 = 0, sum2 = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(select[i] && select[j]) 
						sum1 += stat[i][j];
					else if(!select[i] && !select[j]) 
						sum2 += stat[i][j];
				}
			}
			min = Math.min(min, Math.abs(sum1 - sum2));
			return; 
		}
		
		for(int i=start; i<n; i++) {
			select[i] = true;
			bruteforce(i+1, cnt+1);
			select[i] = false;
		}
	}
	
	
}
