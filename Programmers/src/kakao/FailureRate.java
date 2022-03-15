package kakao;

import java.util.Arrays;

/**
 * 2019 KAKAO BLIND RECRUITMENT
 */
public class FailureRate {
	public static void main(String[] args) {
		FailureRate fr = new FailureRate();
		
		System.out.println(Arrays.toString(fr.solution(5, new int[] {2,1,2,6,2,4,3,3})));
		System.out.println(Arrays.toString(fr.solution(5, new int[] {4,4,4,4,4})));
		System.out.println(Arrays.toString(fr.solution(8, new int[] {1,2,3,4,5,6,7})));
	}
	
	int[] solution(int N, int[] stages){
		int[] cntArr = new int[N+2];
		for(int i : stages) cntArr[i]++;
		
		double[][] failureRate = new double[N+1][3];
		int total = stages.length;
		for(int i=1; i<N+1; i++){
			failureRate[i][0] = i;
			failureRate[i][1] = cntArr[i];
			total -= failureRate[i-1][1];
			failureRate[i][2] = total;
		}
		
		Arrays.sort(failureRate, (o1, o2) -> -Double.compare((o1[2] == 0.0) ? 0.0 : o1[1] / o1[2],
															  (o2[2] == 0.0) ? 0.0 : o2[1] / o2[2]));
		
		return Arrays.stream(failureRate).filter(v -> v[0] > 0).mapToInt(v -> (int)v[0]).toArray();
	}
}
