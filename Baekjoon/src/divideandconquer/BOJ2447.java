package divideandconquer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ2447 {
	
	static char[][] arr;
	static int n;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new char[n][n];
		sc.close();
		
		for(int i=0; i<n; i++) Arrays.fill(arr[i], ' ');
		
		divideAndConquer(0, 0, n);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) bw.write(arr[i][j]);
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		
	}
	
	public static void divideAndConquer(int x, int y, int l) {
		if(l == 1) {
			arr[x][y] = '*';
			return;
		}
		
		int nl = l / 3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i == 1 && j == 1) continue;
				divideAndConquer(x + (i * nl), y + (j * nl), nl);
			}
		}

	}
}
