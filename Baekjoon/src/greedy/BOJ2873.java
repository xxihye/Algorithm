package greedy;

import java.util.*;
import java.io.*;

public class BOJ2873 {

	static int r, c;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	static char[] arr = {'R', 'L', 'D', 'U'};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		int minR = 0, minC = 0, min = 1000;
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if((i + j) % 2 == 1 && map[i][j] < min) {
					min = map[i][j];
					minR = i; minC = j;
				}
			}
		}

		if(r % 2 == 0 && c % 2 == 0) {
			checkRow(minR, minC);
		}else {
			if(r % 2 == 1) {
				for(int i=0; i<r; i++) print(arr[i%2], c); 
			}else {
				for(int i=0; i<c; i++) print(arr[(i%2)+2], r);
			}
			sb.deleteCharAt(sb.length()-1);
		}
		
		System.out.println(sb.toString());
	}
	

	public static void print(char d, int l) {
		for(int i=0; i<l-1; i++) sb.append(d);
		sb.append(l == c ? 'D' : 'R');
	}
	
	public static void checkRow(int minR, int minC) {
		int f = 0;
		
		while(f < r) {
			//첫두행에 있을 때
			if(minR <= f+1) {
				String s = checkColumn(minR, minC);
				if(f+2 != r) s += "D";
				sb.append(s);
				
				for(int i=f+2; i<r; i++) {
					print(arr[(i+1)%2],c);
					if(i == r-1) sb.deleteCharAt(sb.length()-1);
				}
				return;
			}
			else {
				//첫 두행에 없을 때
				for(int i=f; i<= f+1; i++) print(arr[i%2], c);
			}
			f+=2;
		}
	}
	
	public static String checkColumn(int minR, int minC) {
		StringBuilder fsb = new StringBuilder(),
					  lsb = new StringBuilder();
		
		int f = 0, l = c-2;
		
		while(f <= l) {
			if(minC <= f+1 ) {
				if(minC == f ) fsb.append("RD");
				else fsb.append("DR");
				
				if(f == l) break;

				for(int i=f+2; i<=l; i+=2) fsb.append("RURD");
				break;
			}else if(minC >= l){
				if(minC == l) lsb.insert(0, "RD");
				else lsb.insert(0, "DR");
				
				for(int i=f; i<l; i+=2) fsb.append("DRUR");
				break;
			}else {
				fsb.append("DRUR");
				lsb.append("RURD");
				l -= 2;
				f += 2;
			}
			
			
		}
		
		return fsb.toString() + lsb.toString(); 
	}
}
