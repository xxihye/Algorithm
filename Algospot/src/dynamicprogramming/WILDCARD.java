package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WILDCARD {
	
	static String wildCard;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(c-- > 0) {
			wildCard = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			for(int i=0; i<n; i++) {
				String file = br.readLine();
				if(isMatch(file)) sb.append(file + "\n");
			}
			
		}
		
		System.out.println(sb.toString());
		br.close(); 
	}
	
	
	public static boolean isMatch(String file) {
		if(wildCard.toString().equals("*")) return true;
		
		boolean[] chk = new boolean[file.length()];
		char[] wildName = wildCard.toCharArray();
		
		int wildIdx = 0, fileIdx = 0;
		int fileLength = file.length();
		
		while(wildIdx < wildName.length) {
			if(fileIdx == fileLength) break;
			
			if(wildName[wildIdx] == '?') {
				wildIdx++;
				chk[fileIdx++] = true;
			}else if(wildName[wildIdx] == '*') {
				if(++wildIdx == wildName.length) return true;
				
				char ch = wildName[wildIdx++];
				int idx = file.indexOf(ch);
				
				if(idx == -1 || idx < fileIdx) return false;
				
				chk[idx++] = true;
				fileIdx = idx;
			}else {
				if(wildName[wildIdx++] != file.charAt(fileIdx)) return false;
				chk[fileIdx++] = true;
			}
		}
		
		
		return chk[fileLength-1];
	}
}
