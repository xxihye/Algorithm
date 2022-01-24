package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TILING2 {
	
	static final int MOD = 1000000007;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] cache = new int[101];
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		int c = Integer.parseInt(br.readLine());
		for(int i=1; i<=c; i++){
			int width = Integer.parseInt(br.readLine());
			Arrays.fill(cache, -1);
			sb.append(tiling(width));
			if(i < c) sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int tiling(int width){
		if(width <= 1) return 1;
		
		int res = cache[width];
		if(res != -1) return res;
		return cache[width] = (tiling(width - 1) + tiling(width - 2)) % MOD;
	}
	
}
