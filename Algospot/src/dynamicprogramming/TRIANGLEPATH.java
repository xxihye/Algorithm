package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TRIANGLEPATH {
	
	//입력 , dp를 위한 저장공간
	static int[][] triangle, cache;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine());
		
		while(--c >= 0) {
			n = Integer.parseInt(br.readLine());
			triangle = new int[n][n];
			cache = new int[n][n];
			
			StringTokenizer st;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<=i; j++) triangle[i][j] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(maxPath(0,0));
		}
		
		br.close();
	}
	
	public static int maxPath(int x, int y) {
		if(x == n-1) return triangle[x][y];
		
		if(cache[x][y] != 0) return cache[x][y]; 
		
		return cache[x][y] = Math.max(maxPath(x+1, y), maxPath(x+1, y+1)) + triangle[x][y];
	}
}
