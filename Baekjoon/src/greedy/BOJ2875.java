package greedy;

import java.util.Scanner;

public class BOJ2875 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(),
			m = sc.nextInt(),
			k = sc.nextInt();
	
		
		int team = Math.min(n/2, m);
		int r = n + m - (team * 3);
		
		if(k <= r) {
			System.out.println(team);
			return;
		}
		
		k = (int) Math.ceil((double) (k-r) / 3);
		
		System.out.println(team - k);
		
	}
}
