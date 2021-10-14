package devMatching;

import java.util.*;

/**
 * 2021 Dev-Matching : 웹 백엔드 개발 행렬 테두리 회전하기
 */
public class RotateArrayBorder {

	static int[][] arr;
	static int min;

	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		int[] res = solution(rows, columns, queries);
		System.out.println(Arrays.toString(res));
	}

	public static int[] solution(int rows, int columns, int[][] queries) {

		int num = 1;
		arr = new int[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				arr[i][j] = num++;

		
		for(int[] tmp : arr)
			System.out.println(Arrays.toString(tmp));
		System.out.println();
		
		int[] res = new int[queries.length];
		for (int q = 0; q < queries.length; q++) {
			min = 10001;
			arr = rotateBorder(queries[q][0] - 1, queries[q][1] - 1, queries[q][2] - 1, queries[q][3] - 1);
			res[q] = min;
		}

		return res;
	}

	public static int[][] rotateBorder(int x1, int y1, int x2, int y2) {

		int[][] temp = new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) temp[i] = arr[i].clone();
		
		for (int x = x1; x <= x2; x++) {
			for (int y = y2; y >= y1; y--) {
				if (x1 < x && x < x2 && y1 < y && y < y2)
					continue;

				if (x == x1) temp[x][y] = (y == y1) ? arr[x + 1][y] : arr[x][y - 1];
				else if (x == x2)
					temp[x][y] = y == y2 ? arr[x - 1][y] : arr[x][y + 1];
				else
					temp[x][y] = y == y1 ? arr[x + 1][y] : arr[x - 1][y];

				min = temp[x][y] < min ? temp[x][y] : min;
			}
		}

		for (int[] i : temp)
			System.out.println(Arrays.toString(i));

		System.out.println();
		return temp;
	}

}
