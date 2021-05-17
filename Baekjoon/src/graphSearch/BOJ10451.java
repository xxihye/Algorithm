package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10451 {

	static boolean[] chk;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int res = 0,
				n = Integer.parseInt(br.readLine()),
				i = 1;
			arr = new int[n+1];
			chk = new boolean[n+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			for(i=1; i<=n; i++) {
				if(!chk[i]) {
					dfs(i);
					res++;
				}
			}
			
			System.out.println(res);
			
		}

	}
	
	public static void dfs(int i) {
		chk[i] = true;
		
		if(!chk[arr[i]]) dfs(arr[i]); 
	}

}
