package greedy;

import java.io.*;
import java.util.*;

public class BOJ1931 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		LinkedList<Meeting> list = new LinkedList<>();
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Meeting(start, end));
		}
		
		br.close();
		
		Collections.sort(list, new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				if(o1.end > o2.end) return 1;
				else if(o1.end == o2.end && o1.start > o2.start ) return 1;
				return -1;
			}
		});
		
		int end = 0, res = 0;
		
		while(!list.isEmpty()) {
			Meeting tmp = list.poll();
			if(tmp.start >= end) {
				end = tmp.end;
				res++;
			}
		}
		
		System.out.println(res);
		
	}
}

class Meeting{
	int start;
	int end;
	
	Meeting(int start, int end){
		this.start = start;
		this.end = end;
	}
}
