package divideconquer;

import java.util.Scanner;

public class QUADTREE {
	
	static int pointer;
	static String input;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int c = sc.nextInt();
		
		while(c-- > 0) {
			input = sc.next();
			pointer = -1;
			System.out.println(recursive());
		}
	}
	
	
	public static String recursive() {
		pointer++;
		
		if(input.charAt(pointer) != 'x') return input.charAt(pointer) + "";
		
		String leftUp = recursive();
		String rightUp = recursive();
		String leftDown = recursive();
		String rightDown = recursive();
		
		return "x" + leftDown + rightDown + leftUp + rightUp;
	}
}
