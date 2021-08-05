package exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TSP1 {
	
	static int n;
	static double min;
	static boolean[] visited;
	static double[][] dist;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st;
		while(c-- > 0) {
			n = Integer.parseInt(br.readLine().trim());
			visited = new boolean[n];
			dist = new double[n][n];
			min = 12000.0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<n; j++) dist[i][j] = Double.parseDouble(st.nextToken());
			}
			
			for(int i=0; i<n; i++) {
				TSP(i, 0, 1);
				visited[i] = false;
			}
			
			System.out.printf("%.10f \n" , min);
		}
		br.close();
	}
	
	
	public static void TSP(int start, double currDist, int cityNum) {
		visited[start] = true;
		
		if(cityNum == n) {
			min = Math.min(currDist, min);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				TSP(i, currDist + dist[start][i], cityNum+1);
				visited[i] = false;
			}
		}
		
	}
	
}
