package sort;

import java.util.*;
import java.io.*;

public class BOJ10825 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		LinkedList<Student> list = new LinkedList<Student>();
		StringTokenizer st;
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			list.add(new Student(name, kor, eng, math));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(Student s : list) 
			sb.append(s.name + "\n");
		
		System.out.println(sb.toString());
	}
}

class Student implements Comparable<Student>{
	
	int kor;
	int eng;
	int math;
	String name;
	
	public Student() {}

	public Student(String name, int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.name = name;
	}
	
	@Override
	public int compareTo(Student s) {
		if(this.kor < s.kor) return 1;
		else if(this.kor == s.kor && this.eng > s.eng) return 1;
		else if(this.kor == s.kor && this.eng == s.eng && this.math < s.math) return 1;
		else if(this.kor == s.kor && this.eng == s.eng && this.math == s.math ) return this.name.compareTo(s.name);
		
		return -1;
	}
	
}
