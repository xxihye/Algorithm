package simulation;

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

		//chkArr���� path�� ���� �ٲٱ⶧���� ����� �迭�ּҸ� �ѱ��� ������
		//���� ���̳� ���� ���ؼ� �ٲ� �����ͷ� ���� �߸��� �˻縦 �� �� ����
		for (int j = 0; j < n; j++) {
			int[] col = new int[n];
			int[] row = new int[n];
			for (int i = 0; i < n; i++) {
				col[i] = arr[i][j];
				row[i] = arr[j][i]; 
			}

			if (chkArr(col)) cnt++; // �� Ȯ��
			if (chkArr(row)) cnt++; // �� Ȯ��
		}
		System.out.println(cnt);
	}

	//���θ� ���� ��� �ƿ� ū ���� �����ؼ� ���� �� �񱳽� Ʋ������ ��
	//�̹� ���θ� ���� ��쿡 �ݴ� �������� ���θ� ���� ���ϰ� �ϴ� ���
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
					path[i + ck] = 10; // ���θ� ���Ҵٰ� üũ
				}
				i = i + l - 1; // Ž���� ������ ���� ��ġ ����
				if (i >= n) break;
			}
			else if (h - path[i] == -1) {
				now = path[i];
				for (int ck = 1; ck <= l; ck++) {
					if (i - ck < 0 || now - 1 != path[i - ck]) return false; //���� ��ġ�� ũ�����̰� 1�� ���� ���� ���� �� ����
					path[i - ck] = 10;
				}
			}
			h = now;
		}
		return true;
	}
}
