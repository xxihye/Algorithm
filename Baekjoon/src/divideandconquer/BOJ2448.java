package divideandconquer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ2448 {

	static char[][] arr;
	static int n;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new char[n][(2*n)-1];
		sc.close();
		
		for(int i=0; i<n; i++) Arrays.fill(arr[i], ' ');
		divideAndConquer(n-1, 0, (2*n)-1, n);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<n; i++) {
			for(int j=0; j<(2*n)-1; j++) bw.append(arr[i][j]);
			bw.append('\n');
		}
		
		bw.flush();
		bw.close();
		
	}
	
	public static void divideAndConquer(int x, int y, int l, int h) {
		if(l == 5) {
			print(x, y);
			return;
		}
		
		int nl = l / 2;
		int nh = h / 2;
		divideAndConquer(x, y, nl, nh);
		divideAndConquer(x, y+nl+1, nl, nh);
		divideAndConquer(x-nh, y+ l/4 + 1 ,nl, nh);
	}
	
	public static void print(int x, int y) {
		for(int i=0; i<3; i++) {
			if(i == 0) arr[x][y] = arr[x][y+1] = arr[x][y+2] = arr[x][y+3] = arr[x][y+4] = '*';
			else if(i == 1) arr[x-1][y+1] = arr[x-1][y+3] = '*';
			else arr[x-2][y+2] = '*';
		}
		
		return;
	}

}
