package kakao;

import java.util.*;

public class ShuttleBus {
	public static void main(String[] args) {
		ShuttleBus sb = new ShuttleBus();
		System.out.println(sb.solution(1,1,5, new String[] {"08:00", "08:01", "08:02", "08:03"}));
		System.out.println(sb.solution(2, 10, 2 , new String[] {"09:10", "09:09", "08:00"}));
		System.out.println(sb.solution(2, 1, 2, new String[] {"09:00", "09:00", "09:00", "09:00"}));
		System.out.println(sb.solution(1, 1, 5, new String[] {"00:01", "00:01", "00:01", "00:01", "00:01"}));
		System.out.println(sb.solution(1, 1, 1, new String[] {"23:59"}));
		System.out.println(sb.solution(10, 60, 45, new String[] {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
		
		
	}
	
	public String solution(int n, int t, int m, String[] timetable) {
		PriorityQueue<Integer> crew = new PriorityQueue<>();
		for(String time : timetable)
			crew.add(calTime(time));
		
		int start = calTime("09:00");
		ArrayList[] list = new ArrayList[n];
		for(int i=0; i<n; i++){
			list[i] = new ArrayList<>();
			int bus = start + (t * i);
			
			int j=0;
			while(j++ < m && !crew.isEmpty() && crew.peek() <= bus){
				list[i].add(crew.poll());
			}
		}
		
		return (list[n-1].isEmpty() || list[n-1].size() < m) ?
					  convertToString(start + (t * (n-1))) :
					  convertToString(lastTopM(list[n-1], m));
		  
	}
	
	private int lastTopM(ArrayList<Integer> list, int m){
		if(list.get(0).intValue() == list.get(m-1).intValue()) {
			return list.get(0) - 1;
		}
		
		return Math.min(list.get(m-1) - 1, list.get(m-2)+1);
	}
	
	
	private String convertToString(int time){
		return String.format("%02d", time/60) +  ":" + String.format("%02d", time%60);
	}
	
	private int calTime(String time){
		return (time.charAt(0) - '0') * 600 + (time.charAt(1) - '0') * 60 +
		  (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
	}
}
