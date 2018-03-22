package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/30
 * @Description 给出一个无序的正数数组，找出其中没有出现的最小正整数。
 *              如果给出 [1,2,0], return 3； 如果给出 [3,4,-1,1], return 2
 *              要求：只允许时间复杂度O(n)的算法，并且只能使用常数级别的空间。
 */
public class MissingPositive {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        System.out.println(firstMissingPositive(nums)); //print 3
        System.out.println("**********************");
        int[] nums1 = new int[]{2, 1};
        System.out.println(firstMissingPositive(nums1)); //print 3
        System.out.println("**********************");
        int[] nums2 = new int[]{-1, 2, 3, 5};
        System.out.println(firstMissingPositive(nums2)); //print 1
        System.out.println("**********************");
        int[] nums3 = new int[]{1};
        System.out.println(firstMissingPositive(nums3));  //print 2
    }

    //这道题有一个隐含条件：正整数都在n的范围内，否则返回的结果也一定是1到n中的一个，所以可以用下标来代表对应的值
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                if (nums[i] > nums.length || nums[i] < 1 || nums[nums[i] - 1] == nums[i]) {
                    break;
                } else {
                    //当置换的两个数为同一个时，会陷入死循环 比如输入数组为{1, 1},故在上面的判断条件再加一个nums[nums[i] - 1] == nums[i]
                    swap(nums, i, nums[i] - 1);
                }
            }
        }
        System.out.print(nums[0] + " ");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
            if (nums[i] != (i + 1)) {
                System.out.println("");
                return i + 1;
            }
        }
        System.out.println("");
        return nums.length + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
