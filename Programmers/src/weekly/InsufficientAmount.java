package weekly;

public class InsufficientAmount {
	public static void main(String[] args) {
		System.out.println(solution(2500, 1000000000, 2500));
	}

	public static long solution(long price, long money, long count) {
		return Math.max((price * (count * (count + 1) / 2) - money), 0);
	}
}
