package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] cards = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			cards[i] = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		int[] chkCard = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++)
			chkCard[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(cards);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<m; i++) {
			boolean chk = false;
			
			int max = n-1, 
				min = 0,
				mid = 0;
			
			while(max >= min) {
				mid = (max + min) / 2;
				
				if(chkCard[i] == cards[mid]) { 
					chk = true; break;
				}else if(chkCard[i] > cards[mid]) min = mid + 1;
				else max = mid - 1;
			}
			
			if(chk) bw.write("1 ");
			else bw.write("0 ");
		}
		
		bw.close();
		br.close();

	}
	
	

}
