package graphSearch;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ2331 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(),
			p = sc.nextInt();
		
		int[] arr = new int[10];
		
		for(int i=2; i<10; i++) {
			int val = 1;
			for(int j=0; j<p; j++) val *= i;
			arr[i] = val;
		}
		
		arr[1] = 1;
		
		LinkedList<Integer> list = new LinkedList<>();
		int pre = a;
		list.add(pre);
		
		while(true) {
			int val = 0;
			char[] num = Integer.toString(pre).toCharArray();
			for(int i=0; i<num.length; i++) {
				val += arr[(num[i] - '0')];
			}
			
			pre = val;
			if(!list.contains(val)) {
				list.add(val);
			}else break;
		}

		
		System.out.println(list.indexOf(pre));
	}

}
