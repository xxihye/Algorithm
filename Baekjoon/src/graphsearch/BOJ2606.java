package graphsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ2606 {
	
	static int c;
	static Node[] nodes;
	static int vc = -1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		c = Integer.parseInt(br.readLine());
		nodes = new Node[c+1];
		
		
		for(int i=1; i<=c; i++) {
			nodes[i] = new Node(i); 
		}
		
		int pairs = Integer.parseInt(br.readLine());
		for(int i=0; i<pairs; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i1 = Integer.parseInt(st.nextToken());
			int i2 = Integer.parseInt(st.nextToken());
			add(i1, i2);
		}
		
		
		dfs(nodes[1]);
		
		System.out.println(vc);
		
	}
	
	public static void add(int i1, int i2) {
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		
		if(!n1.adjacent.contains(n2)) 
			n1.adjacent.add(n2);
		
		if(!n2.adjacent.contains(n1)) 
			n2.adjacent.add(n1);
		
	}
	
	public static void dfs(Node n) {
		vc++;
		n.visit();
		
		for(Node ad : n.adjacent) {
			if(ad.visit == false) dfs(ad);
		}
		
	}
}


class Node{
	int data;
	boolean visit;
	LinkedList<Node> adjacent;
	
	Node(){}
	
	Node(int data){
		this.data = data;
		this.visit = false;
		this.adjacent = new LinkedList<Node>();
	}
	
	public void visit() {
		this.visit = true;
	}
}