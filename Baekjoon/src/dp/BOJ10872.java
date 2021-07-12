package dp;

import java.util.Scanner;

public class BOJ10872 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		if(n == 0 || n == 1) {
			System.out.println(1);
			return;
		}
		
		int[] arr = new int[n+1];
		arr[0] = arr[1] = 1;
		
		for(int i=2; i<=n; i++) 
			arr[i] = arr[i-1] * i;
		
		System.out.println(arr[n]);
	}
}
