package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/9
 * @Description 找出数组中乘积最大的连续子序列
 */
public class MaxProduct {
    public static void main(String[] args) {

    }
    //本题的关键在于意识到负负得正的情况，单一的存储无法到达目的.
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int maxLocal = nums[0];
        int minLocal = nums[0];
        int global = nums[0];
        for (int i = 1; i < length; i++) {
            int tmp = maxLocal;
            maxLocal = Math.max(Math.max(nums[i], nums[i] * maxLocal), nums[i] * minLocal);
            minLocal = Math.min(Math.min(nums[i], nums[i] * minLocal), nums[i] * tmp);
            global = Math.max(maxLocal, global);
        }
        return global;
    }
}
