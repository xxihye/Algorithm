package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {

	static int n, l, cnt = 0;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		//chkArr에서 path의 값을 바꾸기때문에 복사된 배열주소를 넘기지 않으면
		//다음 행이나 열에 대해서 바뀐 데이터로 인해 잘못된 검사를 할 수 있음
		for (int j = 0; j < n; j++) {
			int[] col = new int[n];
			int[] row = new int[n];
			for (int i = 0; i < n; i++) {
				col[i] = arr[i][j];
				row[i] = arr[j][i]; 
			}

			if (chkArr(col)) cnt++; // 열 확인
			if (chkArr(row)) cnt++; // 행 확인
		}
		System.out.println(cnt);
	}

	//경사로를 놓은 경우 아예 큰 값을 저장해서 다음 값 비교시 틀리도록 함
	//이미 경사로를 놓은 경우에 반대 방향으로 경사로를 두지 못하게 하는 방법
	public static boolean chkArr(int[] path) {
		int h = path[0];
		int now = 0;
		
		for (int i = 0; i < n; i++) {
			if (h == path[i]) continue;

			if (Math.abs(h - path[i]) >= 2) return false;
			else if (h - path[i] == 1) {
				now = path[i];
				for (int ck = 0; ck < l; ck++) {
					if (i + ck >= n || now != path[i + ck]) return false;
					path[i + ck] = 10; // 경사로를 놓았다고 체크
				}
				i = i + l - 1; // 탐색을 시작할 다음 위치 지정
				if (i >= n) break;
			}
			else if (h - path[i] == -1) {
				now = path[i];
				for (int ck = 1; ck <= l; ck++) {
					if (i - ck < 0 || now - 1 != path[i - ck]) return false; //현재 위치와 크기차이가 1일 때만 경사로 놓을 수 있음
					path[i - ck] = 10;
				}
			}
			h = now;
		}
		return true;
	}
}
