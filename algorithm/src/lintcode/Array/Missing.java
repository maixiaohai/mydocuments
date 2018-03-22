package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/30
 * @Description  给出一个包含 0 .. N 中 N 个数的序列，找出0 .. N 中没有出现在序列中的那个数
 *               N = 4 且序列为 [0, 1, 3] 时，缺失的数为2。
 *               要求：在数组上原地完成，使用O(1)的额外空间和O(N)的时间。
 */
public class Missing {
    public static void main(String[] args) {
        int[] nums = new int[]{9,8,7,6,2,0,1,5,4};
        System.out.println(findMissing(nums)); //print 3
        int[] nums1 = new int[]{0};
        System.out.println(findMissing(nums1)); //print 1
    }

    // 原地置换，当nums[i] != i时，特殊情况是，数组长度少一位，所以当nums[i]为数组长度时，不做处理
    public static int findMissing(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] >= nums.length) {
                    break;
                } else {
                    swap(nums[i], i, nums);
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    private static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
