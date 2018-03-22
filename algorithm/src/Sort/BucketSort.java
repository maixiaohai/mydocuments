package Sort;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/2
 * @Description 给定一个未经排序的数组，请找出其排序表中连续两个要素的最大间距。
 *              如果数组中的要素少于 2 个，请返回 0.
 *              要求：用排序的方法解决这个问题是比较简单的方法，
 *              但是排序的时间复杂度是O(nlogn), 能否使用线性的时间和空间复杂度的方法解决这个问题。
 *              思路：桶排序利用函数的映射关系，减少了几乎所有的比较工作。实际上，桶排序的f(k)值的计算，
 *              其作用就相当于快排中划分，已经把大量数据分割成了基本有序的数据块(桶)
 */
public class BucketSort {
    public static void main(String[] args) {
        //给定数组 [1, 9, 2, 5]，其排序表为 [1, 2, 5, 9]，其最大的间距是在 5 和 9 之间，= 4.
        int[] nums = new int[]{1, 9, 2, 5};
        System.out.println(maximumGap(nums));
    }

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        //init bucket
        int bucketCapacity = (max - min) / length + 1;
        int bucketNumber = (max - min) / bucketCapacity + 1;
        int[] minBucket = new int[bucketNumber];
        int[] maxBucket = new int[bucketNumber];
        for (int i = 0; i < bucketNumber; i++) {
            minBucket[i] = Integer.MAX_VALUE;
            maxBucket[i] = Integer.MIN_VALUE;
        }
        for (int i = 0;i < length; i++) {
            int index = (nums[i] - min) / bucketCapacity;
            minBucket[index] = Math.min(minBucket[index], nums[i]);
            maxBucket[index] = Math.max(maxBucket[index], nums[i]);
        }
        int pre = 0;
        int maxDistance = 0;
        for (int i = 1; i < bucketNumber; i++) {
            if (minBucket[i] == Integer.MAX_VALUE && maxBucket[i] == Integer.MIN_VALUE) continue;
            if (minBucket[i] - maxBucket[pre] > maxDistance) {
                maxDistance = minBucket[i] - maxBucket[pre];
            }
            pre = i;
        }
        return maxDistance;
    }
}
