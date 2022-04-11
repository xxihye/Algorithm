package house;

import java.util.*;

public class Solution3_1 {
	public static void main(String[] args) {
		Solution3_1 s = new Solution3_1();
		String[][] variables = {{"template", "{state}"},{"state", "{templates}"}};
		String res = s.soluton("this is {template} {template} is {state}", variables);
		System.out.println(res);
	}
	
	Map<String, String> rules;
	Map<String, List<String>> outEdge;
	private String soluton(String tstring, String[][] variables) {
		rules = new HashMap<>();
		for(String[] v : variables)
			rules.put(v[0], v[1]);
		
		outEdge = new HashMap<>();
		List<String> initVars = new LinkedList<>();
		for(Map.Entry<String, String> e : rules.entrySet()){
			String value = e.getValue();
			if(isVariable(value)){
				String varName = value.substring(1, value.length()-1);  //괄호 제거
				List<String> list = (outEdge.containsKey(varName)) ? outEdge.get(varName) : new LinkedList<>();
				list.add(e.getKey());
				outEdge.put(varName, list);
			}else{
				initVars.add(e.getKey());
			}
		}
		
		for(String var : initVars)
			dfs(var, rules.get(var));
		
		StringBuilder sb = new StringBuilder();
		for(String word : tstring.split(" ")){
			if(isVariable(word)){
				String converted = rules.get(word.substring(1, word.length()-1));
				if(isVariable(converted)){
					sb.append(word + " "); //무한 루프에 빠진 변수
				}else sb.append(converted + " ");
			}else sb.append(word + " ");
		}
		
		return sb.toString();
	}
	
	void dfs(String cur, String changed){
		rules.put(cur, changed);
		
		if(outEdge.get(cur) == null) return;
		
		for(String next : outEdge.get(cur)){
			dfs(next, changed);
		}
	}
	
	boolean isVariable(String value){
		return value.startsWith("{") && value.endsWith("}")
		  && rules.containsKey(value.substring(1, value.length()-1));
	}
	
}
