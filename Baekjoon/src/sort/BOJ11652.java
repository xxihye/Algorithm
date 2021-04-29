package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ11652 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] arr = new long[n];
		
		for(int i=0; i<n; i++) 
			arr[i] = Long.parseLong(br.readLine());
		
		Arrays.sort(arr);
		
		int cnt = 1;
        int max = 1;
        long res = arr[0];
        
        for(int i = 1; i < n; i++) {
            if(arr[i] == arr[i-1]) cnt++; //수가 중복된다면 중복되는 횟수를 체크
            else cnt = 1; // 중복되지 않는다면 1로 다시 초기화
            
            //만약 여태 최대 중복 횟수보다 현재 중복횟수가 더 많다면
            //현재 중복횟수를 최대 중복 횟수로 저장하고
            //중복되는 수를 res에 저장
            //cnt == max를 고려하지 않는 이유는 ? 이미 중복횟수가 같다면 작은 수를 리턴해야되는데
            //이미 arr를 정렬시킨 상태이므로 이전에 저장된 수가 작은 수이므로 고려할 필요가 없음
            if(cnt > max) {
                max = cnt;
                res = arr[i];
            }
        }
        
        System.out.println(res);
	}
}
