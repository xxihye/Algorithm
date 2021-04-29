package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ9012 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(t-- > 0) {
			String[] parenthesis = br.readLine().split(""); 
			Stack<String> stack = new Stack<String>();
			boolean check = true;
			
			for(int i=0; i<parenthesis.length; i++) {
				if(parenthesis[i].equals("(")) stack.push("(");
				else if(!stack.isEmpty()) stack.pop();
				else {
					check = false;
					break;
				}
			}
			
			if(!stack.isEmpty() || !check) sb.append("NO" + "\n"); 
			else sb.append("YES" + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
