package simulation;

import java.io.*;
import java.util.*;

public class BOJ1022 {
	
	static int[][] map;
	static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //�������
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int r1 = sc.nextInt();
		int c1 = sc.nextInt();
		int r2 = sc.nextInt();
		int c2 = sc.nextInt();
		
		int h = r2 - r1 + 1;
		int w = c2 - c1 + 1;
		map = new int[h][w];
		
		int x = 0, y = 0; //������ �迭�� �ε���
		int d = 0; //���� ������ ���� �迭�� �ε���(�ݽð� ���� - �������)
		int num = 1; //����� ����
		int cnt = 0, wCnt = 0, dCnt = 1; //�� �������� ������ Ƚ��, �迭�� ä�� ����, �� �������� ���������ϴ� Ƚ�� 
		while(true) {
			
			if(wCnt >= h*w) break;
			
			if(x >= r1 && x <= r2 && y >= c1 && y <= c2) {
				map[x - r1][y - c1] = num; //map�迭�� �µ��� �ε���ó�� �� ���� ���� 
				wCnt++;
			}
			
			num++;
			cnt++;
			
			//�ҿ뵹�̷� ������� ����ǹǷ� ������ ���ڸ� ������ �ε��� 
			x += dir[d][0];
			y += dir[d][1]; 
			
			if(cnt == dCnt) {
				cnt = 0;
				//�³� ��� ���� ä�� �� ĭ�� �ϳ��� �þ
				if(d == 1 || d == 3) dCnt++;
				d = (d+1) % 4; // ���� ����
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int blank = (int)(Math.log10(num)+1); //�ִ������ �ڸ���
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int len = (int)(Math.log10(map[i][j])+1); //���� ������ �ڸ���
				for (int k = 0; k < blank-len; k++) sb.append(" "); //���ڸ� ��ŭ ��ĭ ä���
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
