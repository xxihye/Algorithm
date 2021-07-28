package greedy;

import java.io.*;
import java.util.*;

public class BOJ1744 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		ArrayList<Integer> negative = new ArrayList<>(); 
		ArrayList<Integer> positve = new ArrayList<>();
		boolean zero = false;
		while(n-- > 0) {
			int i = Integer.parseInt(br.readLine());
			if(i == 0) zero = true;
			else if(i > 0) positve.add(i);
			else negative.add(i);
		}
		
		negative.sort(null);
		positve.sort(Comparator.reverseOrder());
		
		int res = 0;
		int nl = negative.size();
		int pl = positve.size();
		
		if( negative.size() % 2 == 1) {
			nl--;
			if(!zero) res += negative.get(nl); 
		}
		
		if( positve.size() % 2 == 1) res += positve.get(--pl); 
		
		for(int i=0; i<nl; i+=2) res += negative.get(i) * negative.get(i+1);
		 
		for(int i=0; i<pl; i+=2) {
			if(positve.get(i+1) == 1) res += (positve.get(i) + positve.get(i+1));  
			else res += positve.get(i) * positve.get(i+1);
		}
		
		System.out.println(res);
		
		
		
	}
}

