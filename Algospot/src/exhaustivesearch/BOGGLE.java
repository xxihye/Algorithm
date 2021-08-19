package exhaustivesearch;

import java.io.*;
import java.util.*;

public class BOGGLE {
	
	static char[][] boggle;
	static StringBuilder sb = new StringBuilder();
	static int[] dirX = {-1, -1, -1, 0 , 0, 1, 1, 1};
	static int[] dirY = {-1, 0 ,1, -1, 1, -1, 0 ,1};
	
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		boggle = new char[5][5];
		
		int i=0;
		while(C-- > 0) {
			for(; i<5; i++) boggle[i] = sc.next().toCharArray();
			
			int N = sc.nextInt();
			String[] words = new String[N];
			for(i=0; i<N; i++) {
				boolean res = false;
				words[i] = sc.next();
				char c = words[i].charAt(0);
				loop:
				for(int x=0; x<5; x++) {
					for(int y=0; y<5; y++) {
						if(boggle[x][y] == c ) {
							res = hasWord(x, y, words[i], 0);
							if(res) break loop; 
						}
					}
				}
				sb.append(words[i] + " " + (res ? "YES" : "NO") + "\n");
			}
		}
		
		System.out.println(sb.toString());
		

	}
	
	public static boolean hasWord(int x, int y, String word, int index) {
		if(boggle[x][y] != word.charAt(index)) return false;
		else if(word.length()-1 == index) return true;
		
		for(int i=0; i<8; i++) {
			int r = x + dirX[i] , c = y + dirY[i];
			
			if(r >= 0 && r < 5 && c >= 0 && c < 5) {
				if(hasWord(r, c, word, index+1)) return true;
			}
		}
		return false;
	}
}
