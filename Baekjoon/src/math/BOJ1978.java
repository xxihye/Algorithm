package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1978 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) arr[i] = sc.nextInt();
		sc.close();
		
		Arrays.sort(arr);
		ArrayList<Boolean> list = new ArrayList<>();
		list.add(false); list.add(false);
		
		for(int i=2; i<=arr[n-1]; i++) list.add(i, true);
			
		for(int i=2; i<=arr[n-1]; i++) {
			if(list.get(i)) {
				for(int j=i*i; j<=arr[n-1]; j+=i) list.set(j, false);
			}
		}
		
		int res = 0;
		for(int a : arr) if(list.get(a)) res++;
		
		System.out.println(res);
	}
}
