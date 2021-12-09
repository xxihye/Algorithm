package simulation;

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
				//���ڰ� < �� ���, �� ���� �ƴϸ� iter�� ������ �ϳ� �ű�
				if(c == '<') {
					if(iter.hasPrevious()) iter.previous();
				}
				//���ڰ� > �� ���, �� �ڰ� �ƴ� ���� iter �ڷ� �ϳ� �ű�
				else if(c == '>') {
					if(iter.hasNext()) iter.next();
				}
				
				//���ڰ� - �� ��� iter��ġ�� ���� �ϳ� �����
				else if(c == '-') {
					if(iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
				}
				
				//�� ���� ���(���� �빮��, �ҹ���, ����)
				//iter�� ��ġ�� ���� �� 
				else iter.add(c);
			}
			
			for(char c : list) sb.append(c);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
