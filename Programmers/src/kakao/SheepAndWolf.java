package kakao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 출처
 * https://velog.io/@hengzizng/Programmers-92343
 */
public class SheepAndWolf {

	int maxSheepCnt;
	ArrayList<Integer>[] childs;
	
	public int solution(int[] info, int[][] edges){
		childs = new ArrayList[info.length];
		
		for(int[] edge : edges) {
			int p = edge[0];
			int c = edge[1];
			
			if(childs[p] == null) childs[p] = new ArrayList<>();
			
			childs[p].add(c);
		}
		
		List<Integer> nextNodes = new ArrayList<>();
		nextNodes.add(0);
		getAnimal(0,0,0, nextNodes, info);
		
		return maxSheepCnt;
	}
	
	private void getAnimal(int sheepCnt, int wolfCnt, int node, List<Integer> nextNodes, int[] info) {
		sheepCnt += info[node] ^ 1; //XOR 연산
		wolfCnt += info[node];
		maxSheepCnt = Math.max(maxSheepCnt, sheepCnt);
		
		if(sheepCnt <= wolfCnt) return;
		
		List<Integer> copied = new ArrayList<>();
		copied.addAll(nextNodes); //앞으로 갈 곳 복사
		
		//현재 노드의 자식 노드 추가
		if (childs[node] != null) copied.addAll(childs[node]); 		
		
		copied.remove(Integer.valueOf(node)); //현재 노드는 방문했으니 리스트에서 제거
		
		for(int nextNode : copied){
			getAnimal(sheepCnt, wolfCnt, nextNode, copied, info);
		}
	}
	
	
	public static void main(String[] args) {
		SheepAndWolf saw = new SheepAndWolf();
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1}, {1, 2}, {1,4}, {0, 8}, {8, 7}, {9, 10},
		  {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
		
		int res = saw.solution(info, edges);
	}
}
