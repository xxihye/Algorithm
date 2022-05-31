package kakao;

import java.util.*;
import java.util.stream.Collectors;

public class CandidateKey {
	public static void main(String[] args) {
		CandidateKey ck = new CandidateKey();
		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"}
							  ,{"300","tube","computer","3"}, {"400","con","computer","4"}
							  ,{"500","muzi","music","3"}, {"600","apeach","music","2"}};
//		String[][] relation = {{"a", "1", "aaa", "c", "ng"},{"a", "1", "bbb", "e", "g"},{"c", "1", "aaa", "d", "ng"},{"d", "2", "bbb", "d", "ng"}};
		System.out.println(ck.solution(relation));
	}
	
	List<String> list;
	List<List<String>> strList = new ArrayList<>();
	String[][] newRelation;
	int attributes, tuples ;
	
	public int solution(String[][] relation) {
		tuples = relation.length;
		attributes = relation[0].length;
		newRelation = getNewRelation(relation, tuples, attributes);
		list = new ArrayList<>();
		
		for(int i=0; i<attributes; i++){
			int cnt = (int) Arrays.stream(newRelation[i]).distinct().count();
			if(cnt == tuples) strList.add(List.of(String.valueOf(i))); //자기자신으로만 후보키
			else list.add(String.valueOf(i));
		}

		//유일성 체크
		for(int i=2; i<= newRelation.length; i++){
			if(list.size() >= i) comb(i, 0,  "");
		}
		
		//최소성 체크
		int len = strList.size();
		int cnt = 0;
		boolean[] chk = new boolean[len];
		for(int i=1; i<len; i++){
			for(int j=0; j<i; j++){
				if(!chk[j] && strList.get(i).containsAll(strList.get(j))){
					chk[i] = true;
					cnt++;
					break;
				}
			}
		}

		return len - cnt;
	}
	
	public void comb(int size, int start, String s){
		if(s.length() == size){
			if(isUnique(s)) strList.add(Arrays.stream(s.split("")).collect(Collectors.toCollection(ArrayList::new)));
			return;
		}
		
		for(int i=start; i<list.size(); i++)
			comb(size, i+1, s + list.get(i));
		
	}
	
	private boolean isUnique(String s) {
		char[] arr = s.toCharArray();
		Set<String> set = new HashSet<>();
		for(int i=0; i<tuples; i++){
			StringBuilder tmp = new StringBuilder();
			for(char c : arr) tmp.append(newRelation[c - '0'][i]);
			
			if(set.contains(tmp.toString())) return false;
			else set.add(tmp.toString());
		}
		
		return true;
	}
	
	
	private String[][] getNewRelation(String[][] relation, int tuples, int attributes) {
		String[][] arr = new String[attributes][tuples];
		for(int i=0; i<attributes; i++)
			for(int j=0; j<tuples; j++)
				arr[i][j] = relation[j][i];
		return arr;
	}
}
