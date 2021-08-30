package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class STRJOIN {	
	static int n;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		while(--t >= 0) {
			n = Integer.parseInt(br.readLine());
			pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i=0; i<n; i++) pq.add(Integer.parseInt(st.nextToken()));
			
			bw.write(getMinimum() + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	
	public static int getMinimum() {
		int res = 0;
		while(--n > 0) {
			int tmp = pq.poll() + pq.poll();
			pq.add(tmp);
			res += tmp;
		}
		return res;
	}
}
