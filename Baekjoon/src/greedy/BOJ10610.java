package greedy;

import java.util.*;

public class BOJ10610 {
	
	//3의 배수는 모든 자리의 숫자 합이 3의 배수여야 함
	//30의 배수는 10의 배수이므로 0으로 끝나야함
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String n = sc.next();
		
		if(!n.contains("0")) {
			System.out.println("-1");
			return;
		}
		
		int length = n.length();
		int[] arr = new int[length];
		for(int i=0; i<n.length(); i++) arr[i] = n.charAt(i) - '0';
		
		Arrays.sort(arr);
		
		if(arr[0] != 0) {
			System.out.println("-1");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		for(int i=length-1; i>=0; i--) {
			sum += arr[i];
			sb.append(arr[i]);
		}
		
		System.out.println(sum % 3 == 0 ? sb.toString() : "-1");
	}
}
