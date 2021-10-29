package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class SearchRanking {
	
	
	public static void main(String[] args) {
		SearchRanking sr = new SearchRanking();
		String[] info = {"java backend junior pizza 150",
						"python frontend senior chicken 210",
						"python frontend senior chicken 150",
						"cpp backend senior pizza 260",
						"java backend junior chicken 80",
						"python backend senior chicken 50"};
		
		String[] query = {"java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200",
						"cpp and - and senior and pizza 250",
						"- and backend and senior and - 150",
						"- and - and - and chicken 100",
						"- and - and - and - 150" };
		
		System.out.println(Arrays.toString(sr.solution(info, query)));
	}

	public int[] solution(String[] info, String[] queries) {
		HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>>> language = new HashMap<>();
		HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> group;
		HashMap<String, HashMap<String, ArrayList<Integer>>> career;
		HashMap<String, ArrayList<Integer>> food;
		ArrayList<Integer> score;
		
		for(int i=0; i<info.length; i++) {
			String[] tmp = info[i].split(" ");
			
			if(!language.containsKey(tmp[0])) language.put(tmp[0], new HashMap<>()); 
			group = language.get(tmp[0]);
			
			if(!group.containsKey(tmp[1])) group.put(tmp[1], new HashMap<>());
			career = group.get(tmp[1]);
			
			if(!career.containsKey(tmp[2])) career.put(tmp[2], new HashMap<>());
			food = career.get(tmp[2]);
			
			if(!food.containsKey(tmp[3])) food.put(tmp[3], new ArrayList<>());
			score = food.get(tmp[3]);
			score.add(Integer.parseInt(tmp[4]));
		}
		
		for(HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> groupMap : language.values()) 
			for(HashMap<String, HashMap<String, ArrayList<Integer>>> careerMap : groupMap.values()) 
				for(HashMap<String, ArrayList<Integer>> foodMap : careerMap.values()) 
					for(ArrayList<Integer> scoreList : foodMap.values())
						Collections.sort(scoreList);
	
		
		
	
		int idx = 0;
		int[] res = new int[queries.length];
		for(String query : queries) {
			String[] tmp = query.split(" and ");
			int queryScore = Integer.parseInt(tmp[3].split(" ")[1]);
			tmp[3] = tmp[3].split(" ")[0];
			
			for(Entry<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>>> langEntry : language.entrySet()) {
				if(!tmp[0].equals("-") && !tmp[0].equals(langEntry.getKey())) continue;
				
				group = langEntry.getValue();
				for(Entry<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> groupEntry : group.entrySet()) {
					if(!tmp[1].equals("-") && !tmp[1].equals(groupEntry.getKey())) continue;
					
					career = groupEntry.getValue();
					for(Entry<String, HashMap<String, ArrayList<Integer>>> careerEntry : career.entrySet()) {
						if(!tmp[2].equals("-") && !tmp[2].equals(careerEntry.getKey())) continue;
						
						food = careerEntry.getValue();
						
						for(Entry<String, ArrayList<Integer>> foodEntry : food.entrySet()) {
							if(!tmp[3].equals("-") && !tmp[3].equals(foodEntry.getKey())) continue;
							
							score = foodEntry.getValue();
							int n = score.size();
							int min = 0, max = n-1, cnt = n; 
							while(min <= max) {
								int mid = (min + max) / 2;
								
								if(score.get(mid) >= queryScore) {
									max = mid-1;
									cnt = mid;
								}
								else min = mid + 1;
							}
							res[idx] += n - cnt;
						}
					}
				}
			}
			idx++;
		}
		
		return res;
	}
	
}
