package string;

public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		if (x < 0) return false;

		try {
			return x == Integer.parseInt(new StringBuilder(Integer.toString(x)).reverse().toString());
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
