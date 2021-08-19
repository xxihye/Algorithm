package divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FANMEETING {
	
	static int[] C, member, fans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());
		
		while(c--> 0) {
			String m = br.readLine();
			String f = br.readLine();
			
			member = new int[m.length()];
			fans = new int[f.length()];
			
			int N = member.length, M = fans.length;
			
			for(int i=0; i<N; i++) member[i] = m.charAt(i) == 'M' ? 1 : 0;
			for(int i=0; i<M; i++) fans[M-i-1] = f.charAt(i) == 'M' ? 1 : 0; 
				
			C = karatsuba(member, fans);
			
			int res = 0;
			if(C != null) {
				for(int i=N-1; i < M; i++) {
					if(C[i] == 0) ++res;
				}
			}
			
			System.out.println(res);
		}
		
		br.close();
	}
	
	
	public static int[] karatsuba(int[] a, int[] b) {
		int al = a.length, bl = b.length;
		
		if(al < bl) return karatsuba(b, a); //a가 b보다 짧을 경우 둘을 바꿈
		if(al == 0 || bl == 0) return null; //기저사례 : a나 b가 비어있는 경우
		if(al <= 50) return multiply(a, b); //기저사례 : a가 비교적 짧은 경우 O(n^2) 곱셉으로 변경
		int half = al / 2;
		
		int[] a1 = Arrays.copyOf(a, half), a0 = Arrays.copyOfRange(a, half, al);
		int[] b1 = Arrays.copyOf(b, Math.min(bl, half)), b0 = Arrays.copyOfRange(b, Math.min(bl, half), bl);
		
		int[] z2 = karatsuba(a1, b1); //z2 = a1 * b1;
		int[] z0 = karatsuba(a0, b0); //z0 = a0 * b0
		
		a0 = addTo(a0, a1, 0); 
		b0 = addTo(b0, b1, 0);
		//z1 = (a0 + a1) * (b0 + b1) - z0 - z2;
		int[] z1 = karatsuba(a0, b0);
		
		subFrom(z1, z0); subFrom(z1, z2);
		
		int[] res = addTo(null, z0, 0);
		res = addTo(res, z1, half);
		res = addTo(res, z2, half + half);
		
		return res;
	}
	
	
	public static int[] addTo(int[] a, int[] b, int k) {
		int[] res = new int[b.length + k];
		for(int i=0; i<b.length; i++) res[i] = b[i];
		
		if(a == null) return res;
		
		
		
		
		return null;
	}
	
	public static int subFrom(int[] a, int[] b) {
		return 0;
	}
	
	
	public static int[] multiply(int[] a, int[] b) {
		return null;
	}
	
}
