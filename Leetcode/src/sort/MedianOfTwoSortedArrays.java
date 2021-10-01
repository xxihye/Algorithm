package sort;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] arr = new int[nums1.length + nums2.length];
		int index = 0;

		for (int i : nums1) arr[index++] = i;

		for (int j : nums2) arr[index++] = j;

		Arrays.sort(arr);

		int length = arr.length;
		return length % 2 == 1 ? arr[length / 2] : (arr[length / 2] + arr[length / 2 - 1]) / 2.0;
	}
}
