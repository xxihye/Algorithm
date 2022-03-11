package kakao;

import java.util.Arrays;

public class ThanksgivingTraffic {
	
	
	public static void main(String[] args) {
		String[] lines = {
		  "2016-09-15 20:59:57.421 0.351s",
		  "2016-09-15 20:59:58.233 1.181s",
		  "2016-09-15 20:59:58.299 0.8s",
		  "2016-09-15 20:59:58.688 1.041s",
		  "2016-09-15 20:59:59.591 1.412s",
		  "2016-09-15 21:00:00.464 1.466s",
		  "2016-09-15 21:00:00.741 1.581s",
		  "2016-09-15 21:00:00.748 2.31s",
		  "2016-09-15 21:00:00.966 0.381s",
		  "2016-09-15 21:00:02.066 2.62s"
	    };
		ThanksgivingTraffic tt = new ThanksgivingTraffic();
		int res = tt.solution(lines);
		System.out.println(res);
		
	}
	
	public int solution(String[] lines) {
		int length = lines.length;
		Request[] requests = new Request[length];
		
		for (int i = 0; i < length; i++) {
			String[] temp = lines[i].split(" ");
			
			long duration = (long) (Double.parseDouble(temp[2].replace("s", "")) * 1000.0);
			
			String[] time = temp[1].split(":");
			long end = Long.parseLong(time[0]) * 3600 * 1000
			  + Long.parseLong(time[1]) * 60 * 1000
			  + (long) (Double.parseDouble(time[2]) * 1000.0);
			long start = end - duration + 1;
			
			requests[i] = new Request(start, end);
		}
		
		int max = -1;
		for (int i = 0; i < length; i++) {
			long end = requests[i].end + 1000;
			
			int cnt = 1;
			for (int j = i + 1; j < length; j++) {
				if (requests[j].start < end) cnt++;
			}
			
			if (max < cnt) max = cnt;
		}
		
		return max;
	}
}

class Request{
	
	long start, end;
	
	Request(long start, long end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return start + " " + end;
	}
}
