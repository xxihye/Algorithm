package divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FENCE {
	
	static int[] fence;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(c-- > 0) {
			int n = Integer.parseInt(br.readLine());
			fence = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i=0; i<n; i++) fence[i] = Integer.parseInt(st.nextToken());
			
			int max = square(0, n);
			
			sb.append(max + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int square(int p, int r) {
		if(r - p == 1) return fence[p];
		if(p < 0 || r > fence.length || r <= p) return 0;
		
		int q = getMinIndex(p, r); 
		
		int min = fence[q] * (r - p);   
		int left = square(p, q); 
		int right = square(q+1, r);  
		
		return Math.max(min, Math.max(left, right)); 
	}
	
	public static int getMinIndex(int p, int r) {
		int min = fence[p], res = p;
		
		for(int i=p+1; i<r; i++) {
			if(min > fence[i]) {
				min = fence[i];
				res = i;
			}
		}
		return res;
	}
}
