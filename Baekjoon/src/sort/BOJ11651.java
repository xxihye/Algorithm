package sort;

import java.util.*;

public class BOJ11651 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		while(n-- > 0) {
			list.add(new Coordinate(sc.nextInt(), sc.nextInt()));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(Coordinate c : list) {
			sb.append(c.x + " " + c.y + "\n");
		}
		
		System.out.println(sb.toString());
	}

}


