package graphSearch;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ5021 {
	
	static HashMap<String, String[]> family = new HashMap<>(); //가족정보
	static HashMap<String, Double> d = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), M = sc.nextInt();
		String king = sc.next();
		
		for(int i=0; i<N; i++) {
			String child = sc.next();
			String parent1 = sc.next(), parent2 = sc.next();
			
			family.put(child, new String[] {parent1, parent2});
			
			d.put(child, 0.0);
			d.put(parent1, 0.0);
			d.put(parent2, 0.0);
		}
		
		double ans = 0.0;
		String next = "";
		for(int i=0; i<M; i++) {
			String candidate = sc.next();
			init(king);
			double temp = dfs(candidate);
			if(ans < temp) {
				ans = temp;
				next = candidate;
			}
		}
		
		System.out.println(next);
	}
	
	public static void init(String king) {
		for(String s : d.keySet()) d.put(s, 0.0);
		
		d.put(king , 1.0);
	}
	
	public static double dfs(String name) {
		if(!family.containsKey(name)) {
			if(d.containsKey(name)) return d.get(name);
			else return 0.0;
		}
		
		String[] parents = family.get(name);
				
		double ret = (dfs(parents[0]) + dfs(parents[1])) / 2;
		d.put(name, ret);
		return ret;
	}
}

