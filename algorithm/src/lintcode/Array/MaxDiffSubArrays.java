package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/21
 * @Description 给定一个整数数组，找出两个不重叠的子数组A和B，使两个子数组和的差的绝对值|SUM(A) - SUM(B)|最大。返回这个最大的差值。
 *              思路：前后遍历,前遍历找最大值，后遍历找最小值
 */
public class MaxDiffSubArrays {
    public static void main(String[] args) {
        //[1, 2, -3, 1]，返回 6
        int[] nums = new int[]{1, 2, -3, 1};
        System.out.println(maxDiffSubArrays(nums));
        System.out.println(maxDiffSubArrays1(nums));
        int[] nums2 = new int[]{-5,3,-4,0,0,0,-1,20,1,1,-1,-1,-1,-1,-1};
        System.out.println(maxDiffSubArrays(nums2)); // print 29
        System.out.println(maxDiffSubArrays1(nums2));
        int[] nums3 = new int[]{-5,-4};
        System.out.println(maxDiffSubArrays1(nums3));  //print 1
        System.out.println(maxDiffSubArrays1(nums3));
    }

    public static int maxDiffSubArrays(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int length = nums.length;
        int[] leftMax = new int[length];
        int[] leftMin = new int[length];
        int[] rightMax = new int[length];
        int[] rightMin = new int[length];
        //find max
        int localMax = 0;
        int localMin = 0;
        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            localMin = Math.min(nums[i], nums[i] + localMin);
            globalMax = Math.max(globalMax, localMax);
            globalMin = Math.min(globalMin, localMin);
            leftMax[i] = globalMax;
            leftMin[i] = globalMin;
        }
        //find min
        localMax = 0;
        localMin = 0;
        globalMax = Integer.MIN_VALUE;
        globalMin = Integer.MAX_VALUE;
        for (int i = length - 1; i >= 0; i--) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            localMin = Math.min(nums[i], nums[i] + localMin);
            globalMax = Math.max(globalMax, localMax);
            globalMin = Math.min(globalMin, localMin);
            rightMax[i] = globalMax;
            rightMin[i] = globalMin;
        }
        int resLeft = Integer.MIN_VALUE;
        int resRigth = Integer.MIN_VALUE;
        for (int i = 0; i < length - 1; i++) {
            resLeft = Math.max(resLeft, Math.abs(leftMax[i] - rightMin[i + 1]));
            resRigth = Math.max(resRigth, Math.abs(leftMin[i] - rightMax[i + 1]));
        }
        return Math.max(resLeft, resRigth);
    }

    public static int maxDiffSubArrays1(int[] nums) {
        if(nums == null || nums.length ==0)
            return 0;
        int len = nums.length;
        int[] leftMax = new int[len];
        int[] rightMin = new int[len];
        int[] leftMin = new int[len];
        int[] rightMax = new int[len];
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;
        int localMin = 0;
        int globalMin = Integer.MAX_VALUE;

        for(int i=0;i <len ;i++){
            localMax = Math.max(localMax + nums[i],nums[i]);
            globalMax = Math.max(localMax,globalMax);
            leftMax[i] = globalMax;

            localMin = Math.min(localMin + nums[i],nums[i]);
            globalMin = Math.min(localMin,globalMin);
            leftMin[i] = globalMin;
        }

        localMin = 0;
        globalMin = Integer.MAX_VALUE;
        localMax = 0;
        globalMax = Integer.MIN_VALUE;
        for(int i=len-1;i >=0 ;i--){
            localMin = Math.min(localMin + nums[i],nums[i]);
            globalMin = Math.min(localMin,globalMin);
            rightMin[i] = globalMin;

            localMax = Math.max(localMax + nums[i],nums[i]);
            globalMax = Math.max(localMax,globalMax);
            rightMax[i] = globalMax;
        }
        int leftMAX = Integer.MIN_VALUE;
        int rightMAX = Integer.MIN_VALUE;

        for(int i=0;i<len-1;i++){
            leftMAX = Math.max(Math.abs(leftMax[i] - rightMin[i+1]),leftMAX);
            rightMAX = Math.max(Math.abs(leftMin[i] - rightMax[i+1]),rightMAX);
        }

        return Math.max(leftMAX,rightMAX);
    }
}
