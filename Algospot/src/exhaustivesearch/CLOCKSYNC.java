package exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CLOCKSYNC {
	
	static int min;
	static int[] time = new int[16];
	static int[][] switchToClock = {{0,1,2}, {3,7,9,11}, {4, 10, 14, 15},
									{0,4,5,6,7}, {6,7,8,10,12}, {0,2,14,15},
									{3,14,15}, {4,5,7,14,15}, {1,2,3,4,5}, {3,4,5,9,13}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st;
		while(c-- > 0) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<16; i++) time[i] = (Integer.parseInt(st.nextToken())) % 12;
			
			sync(0, 0, time);
			
			if(min == Integer.MAX_VALUE) min = -1;
			System.out.println(min);
		}
		
		br.close();
	}
	
	public static void sync(int switchNum, int cnt, int[] state) {
		if(switchNum == 10) {
			for(int i=0; i<16; i++) {
				if(state[i] != 0) return;
			}
			min = Math.min(min, cnt);
			return;
		}
		
		for(int i=0; i<4; i++) {
			pushSwitch(state, switchNum, i);
			sync(switchNum+1, cnt+i, state);
			pushSwitch(state, switchNum, -i);
		}
	}
	
	public static int[] pushSwitch(int[] state, int switchNum, int i) {
		for(int j=0; j<switchToClock[switchNum].length; j++) {
			int index = switchToClock[switchNum][j];
			state[index] = (state[index] + (3 * i)) % 12;
		}
		
		return state;
	}
	
}
