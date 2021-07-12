package math;

import java.util.Scanner;

public class BOJ11653 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.close();
		
		boolean[] arr = new boolean[n+1];
		
		for(int i=2; i<=n; i++) arr[i] = true;
		
		for(int i=2; i*i<=n; i++) {
			if(arr[i]) for(int j=i*i; j<=n; j+=i) arr[j]= false;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=n; i++) {
			if(arr[i]) {
				while(n % i == 0) {
					sb.append(i + "\n");
					n /= i;
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
