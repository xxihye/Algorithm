package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MaximizeExpression {
	public static void main(String[] args) {
		MaximizeExpression me = new MaximizeExpression();
		long res = me.solution("50*6-3*2");
		System.out.println(res);
	}
	
	int[] priority;
	boolean[] chk;
	long max;
	ArrayList<String> exp;
	
	long solution(String expression){
		priority = new int[3];
		chk = new boolean[3];
		Arrays.fill(priority, -1);
		
		expression = expression.replaceAll("-", " - ");
		expression = expression.replaceAll("\\+", " + ");
		expression = expression.replaceAll("\\*", " * ");
		
		String[] split = expression.split(" ");
		exp = (ArrayList<String>) Arrays.stream(split).collect(Collectors.toList());
		
		max = -1;
		perm(0);
		
		return max;
	}
	
	void perm(int idx){
		if(idx == 3) {
			progress();
			return;
		}
		
		for(int i=0; i<3; i++){
			if(chk[i]) continue;
			
			priority[idx] = i;
			chk[i] = true;
			
			perm(idx+1);
			
			priority[idx] = -1;
			chk[i] = false;
		}
	}
	
	void progress(){
		String[] operator = {"+", "-", "*"};// (+ : 0), (- : 1),(* : 2)
		ArrayList<String> list = new ArrayList<>();
		list.addAll(exp);
		
		for(int i : priority){
			String op = operator[i];
			
			while(list.size() > 2 ){
				int idx = list.indexOf(op);
				if(idx == -1) break;
				
				long operand1 = Long.parseLong(list.get(idx-1));
				long operand2 = Long.parseLong(list.get(idx+1));
				long ans = cal(operand1, operand2, op);
				
				list.remove(idx);
				list.add(idx, String.valueOf(ans));
				list.remove(idx+1);
				list.remove(idx-1);
			}
		}
		
		long res = Math.abs(Long.parseLong(list.get(0)));
		System.out.println(res);
		max = Math.max(res, max);
	}
	
	long cal(long operand1, long operand2, String op){
		if(op.equals("+")) return operand1 + operand2;
		else if(op.equals("-")) return operand1 - operand2;
		else return operand1 * operand2;
	}
}
