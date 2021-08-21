package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class WILDCARD {
	
	static char[] wildCard, file;
	static LinkedList<String> list;
	static int[][] cache;
	//0 : 계산되지 않음, 1 : 해당 입력이 서로 대응됨, -1 : 계산불일치
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine());
		
		
		while(c-- > 0) {
			wildCard = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			list = new LinkedList<>();
			for(int i=0; i<n; i++) {
				cache = new int[101][101];
				String fileName = br.readLine();
				file = fileName.toCharArray();
				if(match(0, 0) == 1) list.add(fileName);
			}
			
			list.sort(null);
			for(String s : list) System.out.println(s);
		}
		
		br.close(); 
	}
	
	
	public static int match(int w, int f) {
		//DP
		if(cache[w][f] != 0) return cache[w][f];
		
		//1:1 대응, 일치하면 인덱스 증가 후 재귀호출
		if(f < file.length && w < wildCard.length )
			if(wildCard[w] == '?' || wildCard[w] == file[f]) 
				return cache[w][f] = match(w+1, f+1);
		
		
		//와일드카드와 파일 명 모두 끝에 도달, 파일명도 끝에 도달 해야만 일치
		if(w == wildCard.length) 
			return cache[w][f] = (f == file.length ? 1 : -1);
		
		//*문자를 만난 경우
		if(wildCard[w] == '*') {
			if(match(w+1, f) == 1 || (f < file.length && match(w, f+1) == 1)) {
				return cache[w][f] = 1;
			}
		}

		return cache[w][f] = -1;
	}
}
