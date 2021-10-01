package implement;

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		if (height == null || height.length <= 1) return 0;

		int maxArea = 0;
		int l = 0, r = height.length - 1;

		while (l != r) {
			if (height[l] <= height[r]) maxArea = Math.max(maxArea, (r - l) * height[l++]);
			else maxArea = Math.max(maxArea, (r - l) * height[r--]);
		}

		return maxArea;
	}
}
