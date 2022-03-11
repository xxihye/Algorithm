package kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewsClustering {
	
	public static void main(String[] args) {
		NewsClustering nc = new NewsClustering();
		int res = nc.solution("E=M*C^2", "e=m*c^2");
		System.out.println(res);
	}
	
	public int solution(String str1, String str2) {
		ArrayList<String> list = makeList(str1);
		HashMap<String , Integer> map = makeMap(str2);
		
		if(list.isEmpty() && map.isEmpty()) return 65536;
		
		double inter = 0.0, union = list.size();
		for(String s : list){
			if(map.containsKey(s)){
				inter++;
				int v = map.get(s);
				if(v == 1) map.remove(s);
				else map.put(s, v-1);
			}
		}
		
		for(Map.Entry<String, Integer> e : map.entrySet()){
			union += e.getValue();
		}
		
		double res = (inter / union) * 65536;
		
		return (int)Math.floor(res);
	}
	

	
	ArrayList<String> makeList(String str){
		ArrayList<String> list = new ArrayList<>();
		char[] arr = str.toLowerCase(Locale.ROOT).toCharArray();
		
		for(int i=0; i<arr.length-1; i++){
			if(arr[i] >= 'a' && arr[i] <= 'z' &&
			  arr[i+1] >= 'a' && arr[i+1] <= 'z'){
				String s = arr[i] + "" + arr[i+1];
				list.add(s);
			}
		}
		return list;
	}
	
	HashMap<String, Integer> makeMap(String str){
		HashMap<String, Integer> map = new HashMap<>();
		char[] arr = str.toLowerCase(Locale.ROOT).toCharArray();
		for(int i=0; i<arr.length-1; i++){
			if(arr[i] >= 'a' && arr[i] <= 'z' &&
			  arr[i+1] >= 'a' && arr[i+1] <= 'z'){
				String s = arr[i] + "" + arr[i+1];
				map.put(s, map.getOrDefault(s, 0)+1);
			}
		}
		return map;
	}
}
