package string;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ11656 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		LinkedList<String> list = new LinkedList<>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		list.add(sb.toString());
		
		for(int i=0; i<str.length()-1; i++) {
			sb.deleteCharAt(0);
			list.add(sb.toString());
		}
		
		list.sort(null);
		
		StringBuilder sb2 = new StringBuilder();
		for(String s : list) 
			sb2.append(s + "\n");
		
		System.out.println(sb2.toString());
	}

}
