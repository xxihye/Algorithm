package implement;

import java.util.Scanner;

public class BOJ2920 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[8];
		String answer = "mixed";
		for(int i=0; i<arr.length; i++) {
			arr[i] = sc.nextInt();
			if(!(arr[0] == 1 || arr[0] == 8)) break;
			
			if(i > 0) {
				if(arr[i] - arr[i-1] == 1) answer = "ascending";
				else if(arr[i] - arr[i-1] == -1) answer = "descending";
				else {
					answer = "mixed";
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
}
