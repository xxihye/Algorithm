package bruteforce;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10819 {
	
	static int n, max = 0;
	static boolean[] chk;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		
		Permutation(arr, 0, n-1);
		
		System.out.println(max);
	}
	
	public static void Permutation(int[] array, int start, int end) {
		if(start == end) {
			max = Math.max(max, sum(array));
			return;
		}
		
		for(int i=start; i<=end; i++) {
			swap(start, i, array); 
			Permutation(array, start+1, end);
			swap(start, i, array); 
		}
	}
	
	public static int sum(int[] tmp) {
		int res = 0;
		for(int i=0; i<n-1; i++) res += Math.abs(tmp[i] - tmp[i+1]);
		return res;
	}
	
	public static void swap(int a, int b, int[] array) {
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
			
}
