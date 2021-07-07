package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1934 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(n--> 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			int gcd = gcd(a, b);
			sb.append(lcm(a, b, gcd) + "\n");
		}
		
		br.close();
		System.out.println(sb.toString());
		
	}
	
	public static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
	
	public static int lcm(int a, int b, int gcd) {
		return (a / gcd) * b; 
	}
}
