package graphSearch;

import java.util.Scanner;

public class BaekJoon6603 {

	static int N;
	static int[] arr;
	static boolean[] result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			N = sc.nextInt();

			if (N == 0) break; //종료 조건
			
			arr = new int[N];
			result = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}

			DFS(0, 0);
			System.out.println();
		}
	}

	/**
	 * @param start : arr[start]의 요소는 사용한다는 의미로 -> result[]에 T를 저장하므로 depth가 6일 때 T인 요소만 출력하는 것!
	 * 				   반복문을 통해서 꼭 사용하는 요소를 바꿔가면서 탐색함.
	 * 				  (x개를 골랐다면 n-x개에서 6-x개를 골라야함)  
	 * @param depth : 총 6개의 숫자를 골라야하므로 0~5번째까지 숫자를 고르고 6번째에서 탈출! 
	 * 				  (depth가 6이라면 6개를 다 골랐다는 뜻
	 * 				    -> 배열의 인덱스는 0부터 시작하므로 0~5 = 6개이므로 6개의 요소가 사용되므로 result[]에 T가 6개 저장돼있음)
	 * 
	 * result[] : 해당 요소를 사용한다는 것을 depth가 6일 때 확인하는 용도로 해당 메소드가 끝나 return되면 다음 단계 반복문에서 사용해야 하므로 F처리
	 */
	private static void DFS(int start, int depth) {
		if (depth == 6) {
			for (int i = 0; i < N; i++) {
				if (result[i]) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
		}

		for (int i = start; i < N; i++) {
			result[i] = true;
			DFS(i + 1, depth + 1);
			// 출력하고 돌아올땐 다른 숫자가 start일 때 사용해야 하기 때문에 F 처리
			result[i] = false;
		}

	}

}


