package simulation;

import java.util.*;

public class BOJ14719 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		
		int[] arr = new int[w];
		for(int i=0; i<w; i++) arr[i] = sc.nextInt();

		LinkedList<Integer> leftList = new LinkedList<>(),
							rightList = new LinkedList<>();
		
		int max = 0;
		for(int i=0; i<w; i++) {
			if(max < arr[i] || (max == arr[i] && max != 0)) {
				max = arr[i];
				leftList.add(i);
			}
		}
		
		max = 0;
		for(int i=w-1; i>=0; i--) {
			if(max < arr[i]) {
				max = arr[i];
				rightList.add(i);
			}
		}
		
		int ans = 0, height, left , right;
		if(leftList.size() > 1) {
			left = leftList.poll();
			height = arr[left];
			
			while(!leftList.isEmpty()) {
				right = leftList.peek();
				for(int i=left+1; i<right; i++) 
					ans += height - arr[i];
				left = leftList.poll();
				height = arr[left];
			}
		}
		
		if(rightList.size() > 1) {
			right = rightList.poll();
			height = arr[right];
			
			while(!rightList.isEmpty()) {
				left = rightList.peek();
				for(int i=left+1; i<right; i++) 
					ans += height - arr[i];
				right = rightList.poll();
				height = arr[right];
			}
		} 
		
		System.out.println(ans);
	}
}
