package implement;

public class ReverseInteger {
	public int reverse(int x) {
		long rev = 0;
		boolean flag = x >= 0 ? true : false; // ¾ç¼ö : true;

		for (int i = Math.abs(x); i > 0; i /= 10) rev = rev * 10 + i % 10;

		if (rev > Integer.MAX_VALUE) return 0;

		return flag ? (int) rev : (int) -rev;
	}
}
