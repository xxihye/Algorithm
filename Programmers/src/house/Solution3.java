package house;

import java.util.HashMap;
import java.util.HashSet;

public class Solution3 {
	
	public static void main(String[] args) {
		Solution3 s = new Solution3();
//		String[][] variables = {{"template", "{state}"},{"state", "{templates}"}};
		String[][] variables = {{"template", "string"},{"state", "templates"}};
		String res = s.soluton("this is {template} {template} is {state}", variables);
		System.out.println(res);
	}
	
	public String soluton(String tstring, String[][] variables){
		//10만
		HashMap<String, String> map = new HashMap<>();
		for(String[] v : variables){
			map.put(v[0], v[1]);
		}
		
		StringBuilder sb;
		HashSet<String> set = new HashSet<>();
		String pre = "";
		boolean chk = false;
		while(true){
			if(!tstring.contains("{") || set.contains(tstring)) break;

			set.add(tstring);
			sb = new StringBuilder();
			int start = tstring.indexOf("{");
			int end = tstring.indexOf("}");
			
			String key = tstring.substring(start+1, end);
			String value = map.get(key);
			
			if(value == null){
				pre += tstring.substring(0, end+1);
				tstring = tstring.substring(end+1);
				chk = true;
				continue;
			}
			
			sb.append(tstring, 0, start);
			sb.append(value + tstring.substring(end+1));
			if(chk) chk = !chk;
			
			tstring = sb.toString();
		}
		
		return pre + tstring;
	}
}
