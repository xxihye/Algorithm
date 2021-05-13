package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ10820 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = "";
		
		while ((input = br.readLine()) != null) {

			int[] count = new int[4];
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == ' ') count[3]++;
				else if (input.charAt(i) >= 'a') count[0]++;
				else if (input.charAt(i) >= 'A') count[1]++;
				else count[2]++;
			}

			for(int i : count)
				sb.append(i + " ");
			
			System.out.println(sb.toString());
		}
		

	}

}