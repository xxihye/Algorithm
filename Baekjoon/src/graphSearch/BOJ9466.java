package graphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9466 {
	
	static int[] arr;
	static boolean[] visited, done; //현재 탐색에서의 방문여부, 팀 확인 여부
	static int c; //팀 확정된 학생 수
	
	/**
	 * 시간 초과 문제를 위해서 dfs 재귀호출 방식
	 * 
	 * visited는 현재 탐색에서의 노드 방문 여부를 저장 ,, dfs() 종료 직전에 false처리로 초기화됨
	 * 현재 탐색에서 방문한 노드의 visited[i]가 true라면 이미 방문했는데 방문을 한 것은 = cycle => 팀이 된 학생 수 증가시키기
	 * 
	 * done[i]이 false라면 아직 팀 배정 결과가 나오지 않은 것.. 탐색해야 함
	 * true라면 팀 배정 결과가 나온 것 - 팀이 됨, 팀이 되지 않음...
	 * 
	 * 탐색 후에는 팀 배정 결과가 나온 것이므로 done[i] = true 처리
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int i = 1,
				n = Integer.parseInt(br.readLine());
			
			arr = new int[n+1];
			visited = new boolean[n+1];
			done = new boolean[n+1];
			c = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			for(; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			for(i=1; i<=n; i++) if(!done[i]) dfs(i);
			
			System.out.println(n - c);
		}
		
	}
	
	public static void dfs(int i) {
		if(visited[i]) {
			done[i] = true;
			c++;
		}else visited[i] = true;
		
		if(!done[arr[i]]) dfs(arr[i]);
		
		visited[i] = false;
		done[i] = true;
	}
}
