package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10799 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] arr = br.readLine().toCharArray();
		
		int height = 0, res = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == '(' && arr[i+1] != ')') { 
				height++;
				res++;
			}else if(arr[i] == ')') {
				if(arr[i-1] == '(') res += height;
				else height--;
			}
			System.out.println(res);
		}

		System.out.println(res);
	}

}
