package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ14891 {
	
	static int[][] gears = new int[4][8]; // 0: N극, 1 : S극
	static int[] dir = new int[4]; // 1 : 시계방향, -1 : 반시계방향 
	static boolean[] isRotate = new boolean[4];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		for(int i=0; i<gears.length; i++) {
			str = br.readLine();
			for(int j=0; j<str.length(); j++) 
				gears[i][j] = str.charAt(j) - '0';
		}
		
		int k = Integer.parseInt(br.readLine());
		while(k-- > 0) {
			String[] tmp = br.readLine().split(" ");
			int num = Integer.parseInt(tmp[0]) - 1;
			int value = Integer.parseInt(tmp[1]);
			set(num, value);
			
			for(int i=0; i<4; i++) {
				if(!isRotate[i]) continue;
				rotate(i);
			}
		}
		
		int res = 0;
		for(int i=0; i<4; i++) {
			if(gears[i][0] == 1) res += Math.pow(2, i);
		}
		System.out.println(res);
	}
	
	public static void rotate(int i) {
		int d = dir[i];
		int[] tmp = new int[8];
		
		for(int j=0; j<8; j++) 
			tmp[j] = d == 1 ? gears[i][(j-d+8)%8] : gears[i][(j-d)%8];
		
		gears[i] = tmp.clone();
		return;
	}
	
	
	public static void set(int num, int value) {
		
		isRotate[num] = true;
		dir[num] = dir[(num+2)%4] = value;
		dir[(num+3)%4] = dir[(num+1)%4] = -value; 
		
		switch(num) {
		case 0 : 
			isRotate[1] = (gears[0][2] != gears[1][6]);   
			isRotate[2] = isRotate[1] && (gears[1][2] != gears[2][6]);
			isRotate[3] = isRotate[2] && (gears[2][2] != gears[3][6]);
			break;
		case 1 :
			isRotate[0] = (gears[0][2] != gears[1][6]);
			isRotate[2] = (gears[1][2] != gears[2][6]);
			isRotate[3] = isRotate[2] && (gears[2][2] != gears[3][6]);
			break;
		case 2 : 
			isRotate[1] = (gears[1][2] != gears[2][6]);
			isRotate[0] = isRotate[1] && (gears[0][2] != gears[num-1][6]);
			isRotate[3] = (gears[2][2] != gears[3][6]);
			break;
		case 3 :
			isRotate[2] = (gears[2][2] != gears[num][6]);
			isRotate[1] = isRotate[2] && (gears[1][2] != gears[num-1][6]);
			isRotate[0] = isRotate[1] && (gears[0][2] != gears[num-2][6]);
			break;
		}
		
	}
}
