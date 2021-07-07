
package stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class BOJ1406 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Character> list = new LinkedList<>();

		String s = br.readLine();

		for (int i = 0; i < s.length(); i++)
			list.add(s.charAt(i));

		int n = Integer.parseInt(br.readLine());
		ListIterator<Character> iter = list.listIterator();
		
		while(iter.hasNext()) iter.next();

		while (n-- > 0) {
			String order = br.readLine();

			if (order.contains("L") && iter.hasPrevious())
				iter.previous();
			else if (order.contains("D") && iter.hasNext())
				iter.next();
			else if (order.contains("B") && iter.hasPrevious()) {
				iter.previous();
				iter.remove();
			}else if (order.contains("P")) {
				iter.add(order.charAt(order.length() - 1));
			}
		}
		br.close();

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(Character c : list)
			bw.append(c);
		
		bw.flush();
		bw.close();
	}

}
