package simulation;

import java.util.Scanner;

public class BOJ2455 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int max = 0, now = 0;
		for(int i=0; i<4; i++) {
			now += -sc.nextInt() + sc.nextInt();
			max = max < now ? now : max;
		}
		
		System.out.println(max);
	}
}
