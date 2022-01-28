package kakao;

public class GetPrimeCnt {
	
	public static void main(String[] args) {
		GetPrimeCnt g = new GetPrimeCnt();
		int[] arrN = {437674, 110011};
		int[] arrK = {3, 10};
		
		for(int i=0; i< arrN.length; i++){
			System.out.println(g.solution(arrN[i], arrK[i]));
		}
	}
	
	public int solution(int n, int k) {
        /*
        * 1. n을 k진수로 변환 (String)
          2. k진수로 변환된 문자열을 0을 기준으로 자름
          3. 나눈 숫자가 소수인지 판별하여 answer++
        */
		
		int res = 0;
		String s = Integer.toString(n, k);
		String[] tmp = s.split("0+");
		for(String num : tmp){
			if(num.isEmpty()) continue;
			
			if(isPrimeNumber(Long.parseLong(num))) res++;
		}
		
		return res;
	}
	
	private boolean isPrimeNumber(long num){
		if(num == 1) return false;
		
		for(int i=2; i<= Math.sqrt(num); i++){
			if(num % i == 0) return false;
		}
		
		return true;
	}
}
