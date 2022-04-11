package house;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution1_1 {
	public static void main(String[] args) {
		Solution1_1 s = new Solution1_1();
		String[] res = s.solution("SSSSSSWWWNNNNNN");
		
		for(String str : res){
			System.out.println(str);
		}
	}
	
	class Entry{
		char d;
		int l;
		
		public Entry(char d, int l) {
			this.d = d;
			this.l = l;
		}
	}
	
	Map<Character, Character> map;
	
	public String[] solution(String path){
		List<Entry> list = new LinkedList<>();
		char[] arr = path.toCharArray();
		
		int n = path.length();
		int size = 0;
		for(int i=0; i<n; i++){
			if(i == 0 || arr[i] != arr[i-1]){
				list.add(new Entry(arr[i], 1));
				size++;
			}else {
				list.get(size - 1).l++;
			}
		}
		
		initMap();
		
		String[] answer = new String[size-1];
		int elapsed = 0;
		for(int i=0; i<size-1; i++){
			Entry e = list.get(i);
			String rotateDir = getRotateDir(e.d, list.get(i+1).d);
			int announceTime = getAnnounceTime(elapsed, e.l);
			int m = (e.l <= 5) ? e.l : e.l - 5;
			answer[i] = "Time " + announceTime + ": Go straight " +  (m * 100) + "m and turn " + rotateDir;
			elapsed += e.l;
		}
		
		return answer;
	}
	
	private int getAnnounceTime(int elapsed, int l) {
		return (l <= 5) ? elapsed : elapsed + (l - 5);
	}
	
	String getRotateDir(char now, char next){
		return (map.get(now) == next) ? "right" : "left";
	}
	
	
	void initMap(){
		map = new HashMap<>();
		map.put('E', 'N');
		map.put('N', 'W');
		map.put('W', 'S');
		map.put('S', 'E');
	}
	
	
	
}
