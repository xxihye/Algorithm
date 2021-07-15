package graphSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1991 {
	
	static TreeNode[] tree = new TreeNode[26];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<26; i++) 
			tree[i] = new TreeNode((char)('A' + i));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(n-- > 0) {
			 st = new StringTokenizer(br.readLine());
			 char parent = st.nextToken().charAt(0);
			 char left = st.nextToken().charAt(0), 
				right = st.nextToken().charAt(0);
			 
			 if(left != '.') 
				 tree[parent - 'A'].left = tree[left - 'A']; 
			  
			 if(right != '.')
				 tree[parent - 'A'].right = tree[right - 'A'];
		}
		
		preOrder(tree[0]);
		System.out.println();
		inOrder(tree[0]);
		System.out.println();
		postOrder(tree[0]);
	}
	
	
	public static void preOrder(TreeNode root) {
		System.out.print(root.name);
		if(root.left != null) preOrder(root.left);
		if(root.right != null) preOrder(root.right);
	}
	
	public static void inOrder(TreeNode root) {
		if(root.left != null) inOrder(root.left);
		System.out.print(root.name);
		if(root.right != null) inOrder(root.right);
	}
	
	public static void postOrder(TreeNode root) {
		if(root.left != null) postOrder(root.left);
		if(root.right != null) postOrder(root.right);
		System.out.print(root.name);
	}
	
}


class TreeNode{
	char name;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(char name) {
		super();
		this.name = name;
	}
}