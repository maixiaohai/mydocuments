package lintcode.dynamic;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/16
 * @Description 背包1：在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 *              背包2：给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大
 *              背包3：给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数
 *                    PS:一个数可以在组合中出现多次。数的顺序不同则会被认为是不同的组合。
 */
public class BackPack {
    public static void main(String[] args) {
        int m = 10;
        int[] A = new int[]{3, 4, 8, 5};
        int target = 4;
        int[] nums = new int[]{1, 2, 4};
        System.out.println(backPackVI(nums, target));
    }

    // 这道题和硬币找零本质一样
    //dp[i][j]为当背包总重量为j且有前i个物品时，背包最多装满dp[i][j]的空间。
    //状态转移方程为：dp[i][j] = Math.max(dp[i - 1][j - A[i]] + A[i], dp[i-1][j]);
    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (m == 0) {
            return 0;
        }
        Arrays.sort(A);
        int[][] res = new int[A.length][m + 1];
        for (int i = 0; i < A.length; ++i) {
            res[i][0] = 0;
        }
        for (int j = 1; j <= m; ++j) {
            //如果当前质量小于A[0] 说明能装满的空间为0；如果当前质量大于A[0]，说明背包此时能被装满的最大体积为A[0]
            if (j >= A[0]) {
                res[0][j] = A[0];
            } else {
                res[0][j] = 0;
            }
            for (int i = 1; i < A.length; ++i) {
                if (j < A[i]) {
                    res[i][j] = res[i - 1][j];
                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i - 1][j - A[i]] +A[i]);
                }
            }
        }
        return res[A.length - 1][m];
    }

    // 背包2相比背包1，复杂处在于存的值是体积，约束的条件是质量
    public int backPack2(int m, int[] A, int[] V) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (m == 0) {
            return 0;
        }
        int[][] res = new int[A.length][m + 1];
        for (int i = 0; i < A.length; i++) {
            res[i][0] = 0;
        }
        for (int j = 1; j <= m; ++j) {
            if (A[0] > j) {
                res[0][j] = 0;
            } else {
                res[0][j] = V[0];
            }
            for (int i = 1; i < A.length; ++i) {
                if (j >= A[i]) {
                    res[i][j] = Math.max(res[i - 1][j - A[i]] + V[i], res[i - 1][j]);
                } else {
                    res[i][j] = res[i - 1][j];
                }
            }
        }
        return res[A.length - 1][m];
    }

    public static int backPackVI(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        if (target == 0) {
            return 0;
        }
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i <= target; i++) {
            res[i] = 0;
            for (int j = 0; j <= (i - 1); j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (nums[k] == (i - j)) {
                        res[i] = res[i] + res[j];
                    }
                }
            }
        }
        return res[target];
    }
}