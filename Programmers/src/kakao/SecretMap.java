package kakao;

public class SecretMap {
	public static void main(String[] args) {
		SecretMap sm = new SecretMap();
		sm.solution(new int[] {46, 33, 33, 22, 31, 50}, new int[] {27, 56, 19, 14, 14, 10}, 6);
		sm.solution(new int[] {9, 20, 28, 18, 11}, new int[] {30, 1, 21, 17, 28}, 5);
		
	}
	
	public String[] solution(int[] arr1, int[] arr2, int n){
		String[] answer = new String[n];
		for(int i=0; i<n; i++){
			answer[i] = String.format("%" + n + "s", Integer.toBinaryString(arr1[i] | arr2[i]))
			  .replaceAll("1", "#")
			  .replaceAll("0", " ");
		}
		
		return answer;
	}
	
	
}
