package graphsearch;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon1260 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int vNum = sc.nextInt();
		int eNum = sc.nextInt();
		int startIndex = sc.nextInt();

		Graph g = new Graph(vNum);
		
		while (eNum-- > 0) {
			g.addEdge(sc.nextInt(), sc.nextInt());
		}
		
		
		g.dfs(startIndex);
		g.reset();
		g.bfs(startIndex);
		
	}
	
	
}

class Graph{
	class Node {
		int data;
		boolean visited;
		LinkedList<Node> adjacent;

		Node() {}

		Node(int data) {
			this.data = data;
			this.visited = false;
			this.adjacent = new LinkedList<Node>();
		}
	}
	
	Node[] nodes;
	
	Graph(){}
	
	Graph(int size){
		nodes = new Node[size+1];
		for(int i=0; i<=size; i++) {
			nodes[i] = new Node(i);
		}
	}
	
	void addEdge(int i1, int i2) {
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		
		if(!n1.adjacent.contains(n2))
			n1.adjacent.add(n2);
		
		if(!n2.adjacent.contains(n1))
			n2.adjacent.add(n1);
	}
	
	void visit(Node n) {
		System.out.print(n.data + " ");
	}
	
	void dfs(int index) {
		dfs(nodes[index]);
	}
	
	void dfs(Node n) {
		if(n == null) return;
	
		n.visited = true;
		visit(n);
		
		Collections.sort(n.adjacent,(o1, o2) -> o1.data - o2.data );
		
		for(Node ad : n.adjacent) {
			if(ad.visited == false)
				dfs(ad);
		}
	}
	
	void bfs(int index) {
		Queue<Node> queue = new LinkedList<Graph.Node>();
		
		Node root = nodes[index];
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			n.visited = true;
			
			Collections.sort(n.adjacent,(o1, o2) -> o1.data - o2.data);
			
			for(Node ad : n.adjacent) {
				if(ad.visited == false && !queue.contains(ad)) 
					queue.add(ad);
			}
			
			visit(n);
		}
	}
	
	void reset(){
		for(Node n : nodes) {
			n.visited = false;
		}
		System.out.println();
	}

}
