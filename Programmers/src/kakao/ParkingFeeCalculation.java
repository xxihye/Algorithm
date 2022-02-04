package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParkingFeeCalculation {
	
	final int END_TIME = 1439; // 23:59을 분으로 변환한 것
	int[] fees;
	
	public static void main(String[] args) {
		ParkingFeeCalculation pfc = new ParkingFeeCalculation();
		int[] f = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT",
		                    "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN",
		                    "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] res = pfc.solution(f, records);
		System.out.println(Arrays.toString(res));
	}
	
	/**
	 1. records의 각 행을 " "로 분리해서 시각, 차번호, 입/출차 정보 확인
	 2. 차 번호를 key로 map에 넣음
	 시각을 분 단위로 변환해서 넣으
	 넣으려고 하는 차 번호가 이미 map안에 존재하는 경우
	 해당 value에 true, 주차한 시간을 넣음(출차시각 - 입차시각)
	 3. keySet을 정렬하여 getValue를 했을 때 true인 경우만 리스트에 주차요금 추가
	 4. 해당 리스트 int[]로 변환하여 리턴
	 */
	
	public int[] solution(int[] f, String[] records) {
		fees = f;
		
		HashMap<String, ArrayList<Integer>> map = new HashMap<>();
		for(String record : records){
			String[] r = record.split(" ");
			String num = r[1];
			
			ArrayList<Integer> list = map.containsKey(num) ? map.get(num) : new ArrayList<>();
			list.add(getTime(r[0]));
			map.put(num, list);
		}
		
		ArrayList<Integer> feeList = new ArrayList<>();
		ArrayList<String> keyList = new ArrayList<>(map.keySet());
		keyList.sort(null);
		
		for(String num : keyList){
			List<Integer> timeList = map.get(num);
			if(timeList.size() % 2 != 0) timeList.add(END_TIME); //마지막 출차를 안함 -> 23:59 출차 처리
			
			int totalTime = 0, size = timeList.size();
			for(int i=1; i<size; i+=2){
				totalTime += timeList.get(i) - timeList.get(i-1);
			}
			
			feeList.add(calculFee(totalTime));
		}
		
		return feeList.stream().mapToInt(i -> i).toArray();
	}
	
	private int getTime(String s){
		String[] split = s.split(":");
		return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
	}
	
	private int calculFee(int time){
		if(time <= fees[0]) return fees[1];
		
		return fees[1] + (int)(Math.ceil((time - fees[0]) / (double) fees[2])) * fees[3];
	}
}
