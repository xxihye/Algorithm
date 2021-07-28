package greedy;

import java.util.*;

public class BOJ1783 {
	
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int answer = 1;
		
		System.out.println(getMoveCnt());
	}
	
	public static int getMoveCnt() {
		if(N == 1) return 1;
		else if(N == 2) return Math.min(4, (M+1)/2);
		
		if(M <= 6) return Math.min(4, M);
		
		return M-2;
	}
}
