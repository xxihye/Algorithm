package string;

import java.util.Scanner;

public class BOJ10824 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String a = sc.next() + sc.next();
		String b = sc.next() + sc.next();
		
		System.out.println(Long.parseLong(a) + Long.parseLong(b)); 
		 
	}
}
