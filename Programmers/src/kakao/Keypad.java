package kakao;

public class Keypad {
	static int[] row = { 3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3 }; // 0 ~ 9, *, # 의 행
	static int[] col = { 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2 }; // 0 ~ 9, *, # 의 열
	
	
	public String solution(int[] numbers, String hand) {
		boolean isRight = hand.equals("right");

		int left = 10, right = 11; // 왼손, 오른손이 위치해있는 키패드 번호(* : 10, # : 11)
		int leftCnt, rightCnt;
		StringBuilder sb = new StringBuilder();

		for (int num : numbers) {
			if (num == 1 || num == 4 || num == 7) {
				left = num;
				sb.append('L');
			} else if (num == 3 || num == 6 || num == 9) {
				right = num;
				sb.append('R');
			} else {
				leftCnt = Math.abs(row[left] - row[num]) + Math.abs(col[left] - col[num]);
				rightCnt = Math.abs(row[right] - row[num]) + Math.abs(col[right] - col[num]);

				if (leftCnt < rightCnt || (leftCnt == rightCnt) && !isRight) {
					left = num;
					sb.append('L');
				} else {
					right = num;
					sb.append('R');
				}
			}
		}

		return sb.toString();
	}
}
