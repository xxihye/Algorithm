package stack;

import java.util.Stack;

public class KthMissingPositiveNumber {
	public int findKthPositive(int[] arr, int k) {
		Stack<Integer> stack = new Stack<Integer>();
		int temp = 0;

		for (int i : arr) {
			while (temp < i - 1)
				stack.add(++temp);
			temp++;
		}

		while (stack.size() < k)
			stack.add(++temp);

		return stack.get(k - 1);
	}
}
