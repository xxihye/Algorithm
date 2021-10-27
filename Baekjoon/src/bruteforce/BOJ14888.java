package bruteforce;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ14888 {
	
	static int n, max, min;
	static int[] arr, op, opArr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		op = new int[4]; // +, -, *, /
		opArr = new int[n-1];
		Arrays.fill(opArr, -1);
		
		for(int i=0; i<n; i++) arr[i] = sc.nextInt();
		for(int i=0; i<op.length; i++) op[i] = sc.nextInt();
			
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	
	public static void permutation(int depth) {
		if(depth == n-1) {
			calculate();
			return; 
		}
		 
		for(int i=0; i<4; i++) {
			if(op[i] == 0) continue;
			
			op[i]--;
			opArr[depth] = i;
			permutation(depth+1);
			op[i]++;
			opArr[depth] = -1;
		}
		
	}
	
	public static void calculate() {
		int res = arr[0];
		
		for(int i=1; i<n; i++) {
			if(opArr[i-1] == 0) res += arr[i];
			else if(opArr[i-1] == 1) res -= arr[i];
			else if(opArr[i-1] == 2) res *= arr[i];
			else res /= arr[i];
		}

		max = Math.max(max, res);
		min = Math.min(min, res);
	}
}
