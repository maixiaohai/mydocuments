package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/27
 * @Description  Given n non-negative integers representing an elevation map where the width of each bar is 1,
 *               compute how much water it is able to trap after raining.
 */
public class RainWater {
    public static void main(String[] args) {
        int[] heights = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trapRainWater(heights)); //print 6
    }

    // 思路：一头一尾两个指针，分别找自己这边的局部最高点，只要比最高点小就可以接水
    public static int trapRainWater(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }
        int left = 0;
        int right = heights.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int res = 0;
        while (left <= right) {
            System.out.print("maxLeft : " + maxLeft + " left : " + heights[left]);
            System.out.println(" maxRight : " + maxRight + " right : " + heights[right]);
            maxLeft = Math.max(maxLeft, heights[left]);
            maxRight = Math.max(maxRight, heights[right]);
            if (maxLeft >= maxRight) {
                if (heights[right] < maxRight) {
                    res += maxRight - heights[right];
                }
                right--;
            } else {
                if (heights[left] < maxLeft) {
                    res += maxLeft - heights[left];
                }
                left++;
            }
        }
        return res;
    }
}
