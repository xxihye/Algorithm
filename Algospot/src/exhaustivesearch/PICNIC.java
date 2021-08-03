package exhaustivesearch;

import java.util.*;
import java.io.*;

public class PICNIC {
	
	static int n;
	static boolean[][] areFriends;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim()),
			m = 0;
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(t--> 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			areFriends = new boolean[n][n];
			
			st = new StringTokenizer(br.readLine());
			boolean[] taken = new boolean[n];
			while(m-- > 0) {
				int p1 = Integer.parseInt(st.nextToken()),
					p2 = Integer.parseInt(st.nextToken());
				areFriends[p1][p2] = areFriends[p2][p1] = true;
			}
			sb.append(countPairings(taken) + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int countPairings(boolean[] taken) {
		int firstFree = -1;
		for(int i=0; i<n; i++) {
			if(!taken[i]) {
				firstFree = i;
				break;
			}
		}
		
		if(firstFree == -1) return 1;
		int res = 0;
		
		for(int pairWith = firstFree+1; pairWith<n; pairWith++) {
			if(areFriends[firstFree][pairWith] && !taken[pairWith]) {
				taken[firstFree] = taken[pairWith] = true;
				res += countPairings(taken);
				taken[firstFree] = taken[pairWith] = false;
			}
		}
		
		return res;
	}
}
