package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ4195 {
	
	static HashMap<String, String> network;
	static HashMap<String, Integer> number;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int c = Integer.parseInt(br.readLine());
		while(c-- > 0) {
			int f = Integer.parseInt(br.readLine());
			network = new HashMap<>();
			number = new HashMap<>();
			
			for(int i=0; i<f; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String f1 = st.nextToken(), f2 = st.nextToken();
				
				if(!network.containsKey(f1)) {
					network.put(f1, f1);
					number.put(f1, 1);
				}
				if(!network.containsKey(f2)) {
					network.put(f2, f2);
					number.put(f2, 1);
				}
				
				union(f1, f2);
				
				sb.append(number.get(find(f1)) + "\n");
			}
		}
		System.out.println(sb.toString());
	}
	

	public static void union(String f1, String f2) {
		//�� �� �� ������ �θ� ã�Ƽ�
		//���� ������ �θ� ������ ������ �θ��� �θ�� ����
		f1 = find(f1);
		f2 = find(f2);
		
		if(!f1.equals(f2)){
			network.put(f2, f1);
			number.put(f1, number.get(f1) + number.get(f2));
		}
	}
	
	
	
	public static String find(String f) {
		if(f == network.get(f)) return f;
		
		String p = find(network.get(f));
		network.put(f, p);
		return network.get(f);
	}
}


