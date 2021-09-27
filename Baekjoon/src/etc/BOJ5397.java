package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class BOJ5397 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		ListIterator<Character> iter;
		LinkedList<Character> list;
		StringBuilder sb = new StringBuilder();
		while(--t >= 0) {
			
			list = new LinkedList<>();
			iter = list.listIterator();
			
			String str = br.readLine();
			for(int i=0; i<str.length(); ++i) {
				char c = str.charAt(i);
				//문자가 < 인 경우, 맨 앞이 아니면 iter를 앞으로 하나 옮김
				if(c == '<') {
					if(iter.hasPrevious()) iter.previous();
				}
				//문자가 > 인 경우, 맨 뒤가 아닌 경우는 iter 뒤로 하나 옮기
				else if(c == '>') {
					if(iter.hasNext()) iter.next();
				}
				
				//문자가 - 인 경우 iter위치의 문자 하나 지우기
				else if(c == '-') {
					if(iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
				}
				
				//그 외의 경우(영어 대문자, 소문자, 숫자)
				//iter의 위치에 삽입 후 
				else iter.add(c);
			}
			
			for(char c : list) sb.append(c);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
