package math;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1929 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m = sc.nextInt(), n = sc.nextInt();
		
		ArrayList<Boolean> list = new ArrayList<Boolean>(n+1);
		list.add(false); // 0 ¼Ò¼ö¾Æ´Ô
		list.add(false); // 1 ¼Ò¼ö¾Æ´Ô
		
		for(int i=2; i<=n; i++) list.add(i, true);
		
		for(int i=2; (i*i)<=n; i++) {
			if(list.get(i)) {
				for(int j=i*i; j<=n; j+=i) list.set(j, false);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=m; i<=n; i++) if(list.get(i)) sb.append(i + "\n");
		
		System.out.println(sb.toString());
	}
}
