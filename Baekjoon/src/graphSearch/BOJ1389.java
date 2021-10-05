package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {
	
	static boolean[][] visited;
	static int[][] kevinBacon;
	static Person[] people;
	static int n, m;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n+1][n+1];
		kevinBacon = new int[n+1][n+1];
		people = new Person[n+1];
		
		int i=1; 
		for(; i<=n; i++) people[i] = new Person(i);
		
		
		for(i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken()),
				p2 = Integer.parseInt(st.nextToken());
			
			people[p1].addPerson(people[p2]);
			people[p2].addPerson(people[p1]);
		}
		
		for(i=1; i<=n; i++) bfs(i);
		
		int res = 0, min = 5000;
		for(i=1; i<=n; i++) {
			int sum = Arrays.stream(kevinBacon[i]).sum();
			if(sum < min) {
				min = sum;
				res = i;
			}
		}
		
		System.out.println(res);
	}
	
	
	public static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		visited[i][i] = true; 
		
		while(!queue.isEmpty()) {
			int p = queue.poll();
			
			for(Person adj : people[p].adjacent) {
				if(!visited[i][adj.num]) {
					visited[i][adj.num] = true;
					kevinBacon[i][adj.num] = kevinBacon[i][p] + 1;
					queue.add(adj.num);
				}
			}
		}
		
	}
}


class Person{
	int num;
	LinkedList<Person> adjacent;
	
	public Person(int num) {
		super();
		this.num = num;
		this.adjacent = new LinkedList<Person>();
	}
	
	public void addPerson(Person p) {
		if(!this.adjacent.contains(p)) this.adjacent.add(p);
	}
	
}