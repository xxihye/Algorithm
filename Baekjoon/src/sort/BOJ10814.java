package sort;

import java.io.*;
import java.util.*;

public class BOJ10814 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<Member> list = new ArrayList<Member>();
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken(), i));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(Member m : list) 
			sb.append(m.age + " " + m.name + "\n");
		
		System.out.println(sb.toString());
	}

}

class Member implements Comparable<Member>{
	
	int age;
	String name;
	int no;
	
	Member() {}
	
	public Member(int age, String name, int no) {
		this.age = age;
		this.name = name;
		this.no = no;
	}

	//나이 오름차순
	//나이가 같으면 번호 오름차순
	public int compareTo(Member m) {
		if(this.age > m.age) return 1;
		else if(this.age == m.age && this.no > m.no) return 1;
		
		return -1;
	}

	
		
	
}