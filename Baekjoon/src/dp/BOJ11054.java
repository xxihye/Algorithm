package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054 {

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] seq = new int[n];
		int[] inc = new int[n];
		int[] dec = new int[n];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<n; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
			inc[i] = 1;
			for(int j=0; j<i; j++) {
				if(seq[j] < seq[i] && inc[j] >= inc[i])
					inc[i] = inc[j] + 1;
			}
		}
		
		System.out.println(Arrays.toString(inc));
		
		for(int i=n-2; i>=0; i--) {
			for(int j=n-1; j>i; j--) {
				if(seq[j] < seq[i] && dec[i] <= dec[j])
					dec[i] = dec[j] + 1;
			}
		}
		
		int max = -1;
		for(int i=0; i<n; i++) {
			int temp = dec[i] + inc[i];
			max = Math.max(temp, max);
		}
		
		System.out.println(max);
		
	}

}
