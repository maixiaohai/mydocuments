package lintcode.dynamic;

import java.util.ArrayList;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/17
 * @Description 给一个整数数组，调整每个数的大小，使得相邻的两个数的差不大于一个给定的整数target，
 *              调整每个数的代价为调整前后的差的绝对值，求调整代价之和最小是多少
 *              PS:你可以假设数组中每个整数都是正整数，且小于等于100

 */
public class MinAdjustmentCost {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add(4);
        a.add(2);
        a.add(3);
        int target = 1;
        System.out.println(MinAdjustmentCost(a, target));
    }

    public static int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        if (A == null || A.size() == 0) {
            return -1;
        }
        //记录调整绝对值的数组
        int[][] res = new int[101][A.size()];
        for (int i = 0; i < 101; i++) {
            res[i][0] = Math.abs(i - A.get(0));
        }
        for (int j = 1; j < A.size(); j++) {
            for (int i = 0; i < 101; i++) {
                res[i][j] = Integer.MAX_VALUE;
                int left = Math.max(0, i - target);
                int right = Math.min(100, i + target);
                int diff = Math.abs(i - A.get(j));
                for (int k = left; k <= right; k++) {
                    res[i][j] = Math.min(res[i][j], res[k][j - 1]+diff);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 101; i++) {
            result = Math.min(res[i][A.size() - 1], result);
        }
        return result;
    }
}
