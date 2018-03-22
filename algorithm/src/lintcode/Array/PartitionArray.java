package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/28
 * @Description 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
 *              所有小于k的元素移到左边，所有大于等于k的元素移到右边，返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
 *              PS：如果数组 nums 中的所有元素都比 k 小，则返回 nums.length。
 */
public class PartitionArray {
    public static void main(String[] args) {
        //给出数组 nums = [3,2,2,1] 和 k = 2，返回 1.
        int[] nums = new int[]{3, 2, 2, 1};
        System.out.println(partitionArray(nums, 2));
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] + " ");
        }
    }

    public static int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left  = 0;
        int right = nums.length - 1;
        for (int i = 0; i <= right;) {
            if (nums[i] < k && i >= left) {
                exch(nums, i++, left++);
            } else if (nums[i] >= k && i <= right) {
                exch(nums, i , right--);
            } else {
                i++;
            }
        }
        return left;
    }

    private static void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
