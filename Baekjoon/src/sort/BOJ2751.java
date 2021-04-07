package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ2751 {
	
	static int n;
	static int[] arr, tmp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmp = new int[n];
		
		for(int i=0; i<n; i++) 
			arr[i] = Integer.parseInt(br.readLine());

		mergeSort(0, n-1);
				
		StringBuilder sb = new StringBuilder();
		for(int i : arr)
			sb.append(i).append('\n');
		
		System.out.println(sb.toString());
	}
	
	private static void mergeSort(int p, int r) {
		if(p >= r) return;
		
		int q = (p + r) / 2;
		
		mergeSort(p, q);
		mergeSort(q + 1, r);
		merge(p, q, r);
	}
	
	private static void merge(int p, int q, int r) {
		int x = p;
		int y = q+1;
		int idx = x;
		
		while(x <= q || y <= r) {
			if(y > r || (x <= q && arr[x] < arr[y]))
				tmp[idx++] = arr[x++];
			else tmp[idx++] = arr[y++];
		}
		
		for(int i=p; i<=r; i++)
			arr[i] = tmp[i];
	}
	
}
