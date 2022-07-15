package kakao;

import java.util.Stack;

public class DartGame {
	public static void main(String[] args) {
		DartGame dg = new DartGame();
		System.out.println(dg.solution("1S2D*3T"));
		System.out.println(dg.solution("1D2S#10S"));
		System.out.println(dg.solution("1D2S0T"));
		System.out.println(dg.solution("1S*2T*3S"));
		System.out.println(dg.solution("1D#2S*3S"));
		System.out.println(dg.solution("1T2D3D#"));
		System.out.println(dg.solution("1D2S3T*"));
	}
	
	public int solution(String dartResult) {
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		
		for(char c : dartResult.toCharArray()){
			if(Character.isDigit(c)){
				num = num * 10 + c - '0';
			}else{
				switch (c){
					case 'D':
						stack.add((int)Math.pow(num, 2));
						break;
					case 'T':
						stack.add((int)Math.pow(num, 3));
						break;
					case 'S':
						stack.add(num);
						break;
					case '*':
						int now = stack.pop();
						if(!stack.isEmpty()) stack.add(stack.pop() * 2);
						stack.add(now * 2);
						break;
					case '#':
						stack.add(-stack.pop());
						break;
				}
				num = 0;
			}
		}
		
		return stack.stream().mapToInt(Integer::intValue).sum();
	}
}
