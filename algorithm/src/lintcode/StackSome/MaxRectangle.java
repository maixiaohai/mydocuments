package lintcode.StackSome;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/10
 * @Description   给你一个二维矩阵，权值为False和True，找到一个最大的矩形，使得里面的值全部为True，输出它的面积
 *                思路： 首先从列的角度把每行的值变为直方图的高度，然后按照最大直方图面积的思路处理。
 */
public class MaxRectangle {
    public static void main(String[] args) {
        boolean[][] matrix = new boolean[][] {
            {true, true, false, false, true},
            {false, true, false, false, true},
            {false, false, true, true, true},
            {false, false, true, true, true},
            {false, false, false, false, true}
        };
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j] == true ? 1 : 0;
                } else {
                    dp[i][j] = matrix[i][j] == true ? (dp[i - 1][j] + 1) : 0;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            int tmp = findRowMax(i, dp);
            res = Math.max(tmp, res);
        }
        return res;
    }
    private static int findRowMax(int row, int[][] dp) {
        int j = 1;
        int max = dp[row][0];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        while (j < dp[row].length || (!stack.isEmpty() && j == dp[row].length)) {
            if (j != dp[row].length && (stack.isEmpty() || dp[row][j] >= dp[row][stack.peek()])) {
                stack.push(j++);
            } else {
                int top = dp[row][stack.pop()];
                int currentMax = !stack.isEmpty() ? top * (j - stack.peek() - 1) : top * j;
                max = Math.max(max, currentMax);
            }
        }
        return max;
    }
}
