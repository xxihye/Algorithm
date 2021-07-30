package bruteforce;

import java.util.*;

public class BOJ1107 {
	
	static boolean[] broken;
	static int N, M, maxLength, res;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		maxLength = ("" + N).length() + 1;
		//Integer.toString(N).length();
		
		if(N == 100) {
			System.out.println(0);
			sc.close();
			return;
		}

		broken = new boolean[10];
		res = Math.abs(N - 100);
		
		if(M > 0) {
			for(int i=0; i<M; i++) broken[sc.nextInt()] = true;
		}
		
		click(0, 0);
		
		System.out.println(res);
	}

	public static void click(int length, int channel) {
		if(maxLength < length) return;
		
		if(length != 0) {
			int cur = Math.abs(N - channel) + length;
			res = Math.min(res, cur);
		}
		
		for(int i=0; i<10; i++) {
			if(!broken[i]) click(length + 1, (channel * 10) + i);
		}
	}
	
}


