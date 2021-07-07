package math;

import java.util.Scanner;

public class BOJ1676 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.close();
		
		int cntFive = 0;
		for(int i=1; i<=n; i++) {
			int tmp = i;
			
			while(tmp % 5 == 0) {
				cntFive++;
				tmp /= 5;
			}
		}
		
		System.out.println(cntFive);

	}

}
