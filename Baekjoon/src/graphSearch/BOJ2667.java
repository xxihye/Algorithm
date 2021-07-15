package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2667 {
	
	static int n;
	static int[][] map;
	static boolean[][] check;
	static int index, complexs;
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayList<Integer> complexList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		check = new boolean[n][n];
		complexList = new ArrayList<Integer>();
		
		for(int i=0; i<n; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0; j<n; j++) {
				map[i][j] = arr[j] - '0';
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] != 0 && !check[i][j]) {
					index++;
					dfs(i, j);
					complexList.add(complexs);
					complexs = 0;
				}
			}
		}
		
		Collections.sort(complexList);
		
		System.out.println(complexList.size());
		for(int i=0; i<complexList.size(); i++) {
			System.out.println(complexList.get(i));
		}
	}
	
	public static void dfs(int i, int j){
		check[i][j] = true;
		map[i][j] = index;
		complexs++;
		
		for(int t=0; t<4; t++) {
			int r = i + direction[t][0];
			int c = j + direction[t][1];
			
			if(r >= 0 && c >= 0 && r < n && c < n) {
				if(map[r][c] != 0 && !check[r][c]) {
					dfs(r, c);
				}
			}
		}
		
	}
}
