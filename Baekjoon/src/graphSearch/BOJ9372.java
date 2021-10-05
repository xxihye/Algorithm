package graphSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ9372 {
	
	static Country[] countries;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int c = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		while(--c >= 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()),
				m = Integer.parseInt(st.nextToken()),
				i = 0;
			cnt = 0;
			
			countries = new Country[n+1];
			for(i=1; i<=n; i++) countries[i] = new Country(i);
			
			for(i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int c1 = Integer.parseInt(st.nextToken()),
					c2 = Integer.parseInt(st.nextToken());
				
				countries[c1].addCourse(countries[c2]);
				countries[c2].addCourse(countries[c1]);
			}
			
			for(i=1; i<=n; i++) {
				if(!countries[i].visited) dfs(i);
			}
			
			bw.write(cnt + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int i) {
		Stack<Country> stack = new Stack<>();
		stack.add(countries[i]);
		countries[i].visit();
		
		while(!stack.isEmpty()) {
			Country c = stack.pop();
			
			for(Country adj : c.adjacent) {
				if(!adj.visited) {
					stack.add(adj);
					adj.visit();
					cnt++;
				}
			}
		}
	}
	
}

class Country{
	int num;
	boolean visited;
	LinkedList<Country> adjacent;
	
	public Country(int num) {
		super();
		this.num = num;
		this.visited = false;
		this.adjacent = new LinkedList<>();
	}
	
	public void addCourse(Country c) {
		if(!this.adjacent.contains(c)) 
			this.adjacent.add(c);
	}
	
	public void visit() {
		this.visited = true;
	}
}
