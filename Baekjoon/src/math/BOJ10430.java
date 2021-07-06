package math;

import java.util.Scanner;

public class BOJ10430 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(),
			b = sc.nextInt(),
			c = sc.nextInt();
		
		sc.close();
		StringBuilder sb = new StringBuilder();
		
		//첫째 줄에 (A+B)%C, 둘째 줄에 ((A%C) + (B%C))%C, 셋째 줄에 (A×B)%C, 넷째 줄에 ((A%C) × (B%C))%C를 출력
		sb.append((a + b) % c + "\n");
		sb.append(((a % c) + (b % c)) % c + "\n");
		sb.append((a * b) % c + "\n");
		sb.append(((a % c) * (b % c)) % c + "\n");
		
		System.out.println(sb.toString());
	}
}
