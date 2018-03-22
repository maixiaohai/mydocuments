package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/29
 * @Description  给定一个由 n 个整数组成的数组和一个正整数s, 请找出该数组中满足其和 ≥ s 的最小长度子数组。
 *               如果无解，则返回 -1。
 *               思路：前后两个指针，sum代表后指针加和，当sum>=S时，后指针向前移动，寻找最短子数组
 */
public class MinimumSizeSum {
    public static void main(String[] args) {
        //给定数组 [2,3,1,2,4,3] 和 s = 7, 子数组 [4,3] 是该条件下的最小长度子数组
        int[] nums = new int[]{2,3,1,2,4,3};
        int s = 7;
        System.out.println(minimumSize(nums, s));
    }

    public static int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (end < nums.length) {
            sum += nums[end];
            System.out.println("end " + end + " res " + res);
            while (start <= end && sum >= s) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start++];
                System.out.println("start " + start + " end " + end);
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
