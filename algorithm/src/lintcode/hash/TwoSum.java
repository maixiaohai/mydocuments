package lintcode.hash;

import java.util.Hashtable;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/22
 * @Description Given an array of integers that is already sorted in ascending order,
 *              find two numbers such that they add up to a specific target number.
                The function twoSum should return indices of the two numbers such that they add up to the target,
                where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
                are not zero-based.
 */

public class TwoSum {
    public static void main(String[] args) {
        /*
     * @param nums an array of Integer
     * @param target = nums[index1] + nums[index2]
     * @return [index1 + 1, index2 + 1] (index1 < index2)
     */
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = solution(nums, target);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
        int[] nums1 = new int[]{0, 0, 3, 4};
        target = 0;
        int[] res1 = solution1(nums1, target);
        for (int i = 0; i < res1.length; i++) {
            System.out.println(res1[i]);
        }
    }

    //暴力破解
    public static int[] solution(int[] nums, int target) {
        int[] res = new int[2];
        int index1 = -1;
        int index2 = -1;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((nums[i] + nums[j]) > target) {
                    break;
                }
                if ((nums[i] + nums[j]) == target) {
                    index1 = i;
                    index2 = j;
                    break;
                }
            }
            if (index1 != -1) {
                break;
            }
        }
        res[0] = index1 + 1;
        res[1] = index2 + 1;
        return res;
    }

    // 用hashtable处理，O(n)
    public static int[] solution1(int[] nums, int target) {
        int[] res = new int[2];
        Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (ht.containsKey(target - nums[i])) {
                res[0] = ht.get(target - nums[i]);
                res[1] = i + 1;
                break;
            }
            ht.put(nums[i], i + 1);
        }
        return res;
    }
}
