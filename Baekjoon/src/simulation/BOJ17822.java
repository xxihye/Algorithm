package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822 {
	
	static int[][] disks;
	static int n, m, t;
	static Queue<int[]> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		disks = new int[n][m];
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) disks[i][j] = Integer.parseInt(st.nextToken());
		}
	
		int x, d, k;
		while(t-- > 0){
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			for(int j=x; j<=n; j+=x){
				if(d == 0) rotate(j-1, k);
				else rotate(j-1, m - k);
			}
			
			if(!removeNum()) setAvg();
		}
		
		int sum = 0;
		for(int[] arr : disks) sum += Arrays.stream(arr).sum();
		
		System.out.println(sum);
	}
	
	public static void rotate(int x, int k){
		int tmp;
		
		while(k-- > 0){
			tmp = disks[x][m-1];
			for(int j=m-1; j>0; j--) disks[x][j] = disks[x][j-1];
			disks[x][0] = tmp;
		}
	}
	
	public static boolean removeNum(){
		int[][] copy = new int[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				copy[i][j] = disks[i][j];
		
		boolean chk = false;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(disks[i][j] == disks[i][(j+1) % m] && disks[i][j] != 0){
					copy[i][j] = 0;
					copy[i][(j+1) % m] = 0;
					chk = true;
				}
				
				if(i == n-1) continue;
				if(disks[i][j] == disks[i+1][j] && disks[i][j] != 0) {
					copy[i][j] = 0;
					copy[i+1][j] = 0;
					chk = true;
				}
			}
		}
		
		disks = copy;
		return chk;
	}
	
	public static void setAvg(){
		int sum = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(disks[i][j] > 0) {
					sum += disks[i][j];
					queue.add(new int[] {i, j});
				}
			}
		}
		
		double avg = (sum * 1.0) / queue.size();
		while(!queue.isEmpty()){
			int[] now = queue.poll();
			if(disks[now[0]][now[1]] > avg) disks[now[0]][now[1]]--;
			else if(disks[now[0]][now[1]] < avg) disks[now[0]][now[1]]++;
		}
	}
}
