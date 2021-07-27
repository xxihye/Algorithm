package greedy;

import java.util.*;

public class BOJ11047 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(),
			k = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=n-1; i>=0; i--) arr[i] = sc.nextInt();
		
		int idx = 0;
		int res = 0;
		while(k > 0) {
			while(arr[idx] <= k) {
				k -= arr[idx];
				res++;
			}
			idx++;
		}
		
		System.out.println(res);
		
	}
}
