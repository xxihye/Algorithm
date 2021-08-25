package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JLIS {
	
	static final long INF = Long.MIN_VALUE;
	
	static int[] A, B;
	static int al, bl;
	static int[][] cache;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());
	
		StringTokenizer st;
		while(--c >= 0) {
			st = new StringTokenizer(br.readLine());
			
			al = Integer.parseInt(st.nextToken());
			bl = Integer.parseInt(st.nextToken());
			
			A = new int[al];
			B = new int[bl];
			cache = new int[al+1][bl+1];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<al; ++i) A[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<bl; ++i) B[i] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<=al; ++i)
				for(int j=0; j<=bl; ++j) cache[i][j] = -1;
			
			System.out.println(jlis(-1, -1) - 2);
		}
		br.close();
		
	}
	
	public static int jlis(int idxA, int idxB) {
		
		if(cache[idxA+1][idxB+1] != -1) return cache[idxA+1][idxB+1];
		
		
		long a = (idxA == -1 ? INF : A[idxA]),
			 b = (idxB == -1 ? INF : B[idxB]);
		long maxElement = Math.max(a, b);
		
		int res = 2;
		
		for(int nextA = idxA+1; nextA < al; ++nextA)
			if(maxElement < A[nextA]) res = Math.max(res, jlis(nextA, idxB) + 1);
		
		for(int nextB = idxB+1; nextB < bl; ++nextB)
			if(maxElement < B[nextB]) res = Math.max(res, jlis(idxA, nextB) + 1);
				
		return cache[idxA+1][idxB+1] = res; 
	}
}
