package bruteforce;

import java.util.*;

public class BOJ2304 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		ArrayList<Pillar> list = new ArrayList<Pillar>();
		for(int i=0; i<n; i++) 
			list.add(new Pillar(sc.nextInt(), sc.nextInt()));
		
		Collections.sort(list); //left(x좌표) 기준으로 정렬
		
		int max = 0;
		Stack<Pillar> leftStack = new Stack<Pillar>();
		for(int i=0; i<n; i++) {
			if(max < list.get(i).height) {
				leftStack.add(list.get(i));
				max = list.get(i).height;
			}
		}
		
		max = 0;
		Stack<Pillar> rightStack = new Stack<Pillar>();
		for(int i=n-1; i>=0; i--) {
			if(max < list.get(i).height) {
				rightStack.add(list.get(i));
				max = list.get(i).height;
			}
		}
		
		int ans = (rightStack.peek().left - leftStack.peek().left + 1) * rightStack.peek().height;
		int beforeLeft = leftStack.pop().left;
		while(!leftStack.isEmpty()) {
			int left = leftStack.peek().left;
			int height = leftStack.peek().height;
			ans += (beforeLeft - left) * height;
			beforeLeft = left;
			leftStack.pop();
		}
		
		int beforeRight = rightStack.pop().left + 1;
		while(!rightStack.isEmpty()) {
			int right = rightStack.peek().left + 1;
			int height = rightStack.peek().height;
			ans += (right - beforeRight) * height;
			beforeRight = right;
			rightStack.pop();
		}
		
		System.out.println(ans);
	}
}

class Pillar implements Comparable<Pillar>{
	int left;
	int height;
	
	public Pillar(int left, int height) {
		this.left = left;
		this.height = height;
	}
	
	@Override
	public int compareTo(Pillar o) {
		return this.left < o.left ? -1 : 1; 
	}
}