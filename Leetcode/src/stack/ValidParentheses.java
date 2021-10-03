package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('}', '{');
		map.put(']', '[');
		map.put(')', '(');

		for (char c : s.toCharArray()) {
			if (c == '{' || c == '[' || c == '(') stack.push(c);
			else {
				if (!stack.isEmpty() && stack.peek() == map.get(c)) stack.pop();
				else return false;
			}
		}
		return stack.isEmpty();
	}
}
