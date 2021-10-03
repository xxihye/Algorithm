package implement;

import java.util.Arrays;

public class ThreeSumClosest {
	 public int threeSumClosest(int[] nums, int target) {
         int result = 10000;
		 
		 Arrays.sort(nums);
		 
		 for(int i=0; i<nums.length - 2; i++) {
			 int left = i+1;
			 int right = nums.length - 1;
			 while(left<right) {
				 int sum = nums[i] + nums[left] + nums[right];
				 
				 if(target == sum) return target;
				 
				 if(Math.abs(target-result) > Math.abs(target-sum)) result = sum;

				 if(target > sum) left++;
				 else right--;
			 }
		 }
		 return result;
    }
}
