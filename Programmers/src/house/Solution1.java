package house;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 네비게이션 :
 * 다음 방향 변경 지점까지의 거리가 500m  || 출발 혹은 방향 변경 직후 다음 방향 변경 지점까지의 거리가 500m 이하
 *
 * Time x : Go Straigth ym and turn direction
 */
public class Solution1 {
	
	public static void main(String[] args) {
		Solution1 s = new Solution1();
		String[] res = s.solution("SSSSSSWWWNNNNNN");
		
		for(String str : res){
			System.out.println(str);
		}
	}
	
	static final int MAX_SIZE = 5;
	HashMap<Character, Integer> dirMap;
	static char[] dirArr = {'N', 'E', 'S', 'W'};
	char[] charArr;
	
	public String[] solution(String path){
		dirMap = new HashMap<>();
		for(int i=0; i<4; i++){
			dirMap.put(dirArr[i], i);
		}
		
		charArr = path.toCharArray();
		
		List<String> orderList = getOrders(path);
		
		List<String> res = new ArrayList<>();
		int l = orderList.size();
		int time = 0;
		
		for(int i=0; i<l-1; i++){
			
			String s = orderList.get(i);
			int m = (s.length() <= MAX_SIZE) ? s.length() * 100 : 500;
			
			if((s.length() > MAX_SIZE)) time += (s.length() - MAX_SIZE);
			String dir = isRight(orderList.get(i).charAt(0), orderList.get(i+1).charAt(0));
			res.add("Time " + time + ": Go straight " + m + "m and turn " + dir );
			time += (s.length() > MAX_SIZE) ? MAX_SIZE : s.length();
		}
		
		return res.stream().toArray(String[]::new);
	}
	
	String isRight(char now, char next){
		boolean isRight = false;
		
		int nowIdx = dirMap.get(now);
		int nextIdx = dirMap.get(next);
		
		if( (nowIdx + 1) % 4 == nextIdx) isRight = true;
		
		return isRight ? "right" : "left";
	}
	
	
	public List<String> getOrders(String path){
		List<String> list = new ArrayList<>();
		int length = path.length();
		char pre = charArr[0];
		int start = 0;
		for(int i=1; i< length; i++){
			if (pre != charArr[i]){
				list.add(path.substring(start, i));
				start = i;
				pre = charArr[i];
			}
		}
		
		list.add(path.substring(start, length));
		return list;
	}

}
