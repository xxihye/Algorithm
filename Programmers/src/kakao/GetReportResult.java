package kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GetReportResult {
	
	public static void main(String[] args) {
		GetReportResult g = new GetReportResult();
		String[][] id_list = {{"muzi", "frodo", "apeach", "neo"},
		                        {"con", "ryan"}};
		String[][] report = {{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
		                    {"ryan con", "ryan con", "ryan con", "ryan con"}};
		int[] k = {2, 3};
		
		for(int i=0; i<id_list.length; i++){
			int[] res = g.solution(id_list[i], report[i], k[i]);
			System.out.println(Arrays.toString(res));
		}
	}
	
	public int[] solution(String[] id_list, String[] report, int k) {
		int n = id_list.length;
		
		//유저 : 유저 번호 매핑
		HashMap<String, Integer> userMap = new HashMap<>();
		
		//신고한 유저 : 해당 유저에게 신고당한 유저 리스트
		HashSet<Integer>[] reportedUsers = new HashSet[n];
		
		//유저별 신고당한 횟수 배열
		int[] reportCntArr = new int[n];
		int[] answer = new int[n];
		
		for(int i=0; i<n; i++) {
			userMap.put(id_list[i], i);
			reportedUsers[i] = new HashSet();
		}
		
		for(String r : report){
			String[] tmp = r.split(" ");
			int userId = userMap.get(tmp[0]);
			int reportedId = userMap.get(tmp[1]);
			
			//유저가 이미 같은 사람을 신고했었던 경우
			if(reportedUsers[userId].contains(reportedId)) continue;
			
			reportedUsers[userId].add(reportedId);
			reportCntArr[reportedId]++;
		}
		
		for(int i=0; i<n; i++){
			if(reportedUsers[i].size() == 0) continue;
			
			for(Integer id : reportedUsers[i]) {
				if(reportCntArr[id] >= k) answer[i]++;
			}
		}
		
		return answer;
	}
}
