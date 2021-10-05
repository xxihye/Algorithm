package implement;

import java.util.Scanner;

public class BOJ11005 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong();
		int b = sc.nextInt();
		
		sc.close();
		
		char[] arr = new char[b];
		for(int i=10; i<b; i++) arr[i] = (char)('A' + i - 10); 
		
		StringBuilder sb = new StringBuilder();
		while(n > 0) {
			int r = (int) n % b;
			if(r < 10) sb.append(r);
			else sb.append(arr[r]);
			n /= b;
		}
		
		System.out.println(sb.reverse().toString());
	}
}
