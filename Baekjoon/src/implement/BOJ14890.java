package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {
	
	static int n, l, cnt = 0;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 n = Integer.parseInt(st.nextToken());
		 l = Integer.parseInt(st.nextToken());
		 
		 for(int i=0; i<n; i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j=0; j<n; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		 }
		 
		 for(int j=0; j<n; j++) {
			 int[] col = new int[n];
			 for(int i=0; i<n; i++) col[j] = arr[i][j];
			 
			 if(chkArr(col)) cnt++; //열 확인
			 if(chkArr(arr[j])) cnt++; //행 확인
		 }
		 
	}
	
	public static boolean chkArr(int[] path) {
		
		
		
		
		
		return true;
	}
}
