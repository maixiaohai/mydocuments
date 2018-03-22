package lintcode.dynamic;

import java.util.Arrays;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/14
 * @Description 给定一组不同面额的硬币，以及一个钱数amount，找出最少硬币数以达到这个钱数的方法，找不出组合则返回-1
 */
public class CoinChange {
    // coins=[1, 2, 5] amount=11 return 3, 11=5+5+1
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(solution1(coins, amount));
        System.out.println(solution2(coins, amount));
        int[] coins2 = {2};
        amount = 1;
        System.out.println(solution2(coins2, amount));
    }

    // time limit exceeded (原因：很多中间结果被算了多次，需要把中间结果存起来，用空间换取时间)
    public static int solution1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int res = search(coins.length - 1, coins, amount, 0);
        if (res < Integer.MAX_VALUE) {
            return res;
        } else {
            return -1;
        }
    }

    private static int search(int id, int[] coins, int amount, int counter) {
        if (id < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return counter;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        return Math.min(search(id, coins, amount - coins[id], counter + 1),
                search(id - 1, coins, amount, counter));
    }

    // uniquePath变形版
    public static int solution2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int m = amount + 1;
        int n = coins.length;
        int[][] res = new int[m][n];
        for (int i = 0; i < coins.length; ++i) {
            res[0][i] = 0;
        }
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                int contain = -1;
                if (coins[j] <= i) {
                    contain = res[i - coins[j]][j] == -1 ? -1 : res[i - coins[j]][j] + 1;
                }
                int miss = -1;
                if (j >= 1) {
                    miss = res[i][j - 1];
                }
                if (miss == -1 || contain == -1) {
                    res[i][j] = Math.max(contain, miss);
                } else {
                    res[i][j] = Math.min(contain, miss);
                }
            }
        }
        return res[m - 1][n - 1];
    }
}
