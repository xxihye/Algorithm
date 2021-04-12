package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ11650 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		ArrayList<Coordinate> list = new ArrayList<Coordinate>(n);
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(Coordinate c : list) 
			sb.append(c.x + " " + c.y + "\n");
		
		System.out.println(sb.toString());
	}
}


class Coordinate implements Comparable<Coordinate>{
	int x;
	int y;
	
	Coordinate(){}
	
	Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Coordinate o) {
		if(this.x > o.x) return 1;
		else if(this.x == o.x && this.y > o.y) return 1;
		
		return -1;
	}

	
	
	
}