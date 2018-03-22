package lintcode.StackSome;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/9
 * @Description  Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 *               find the area of largest rectangle in the histogram.
 *               给出 height = [2,1,5,6,2,3]，返回 10
 *               思路：找到当前元素的左右边界
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
        int[] height = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(height));
    }

    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0];
        }
        int res = 0;
        int[] high = Arrays.copyOf(height , height.length + 1);
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < high.length; i++) {
            while (!stack.isEmpty() && high[i] < high[stack.peek()]) {
                int area = high[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                System.out.println("area: " + area + " i : " + i);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
}
