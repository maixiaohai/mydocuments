package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/25
 * @Description  （1）假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。
 *               你可以完成尽可能多的交易(多次买卖股票)。然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。
 *               （2）Design an algorithm to find the maximum profit. You may complete at most two transactions.
 */
public class StockSell {
    public static void main(String[] args) {
        int[] prices = new int[]{2,1,2,0,1};
        System.out.println(maxProfit(prices));// print 2
        int[] prices2 = new int[]{1,2,4};
        System.out.println(maxProfit(prices2)); //print 3
        int[] prices3 = new int[]{4,4,6,1,1,4,2,5};
        System.out.println(maxProfit2(prices3)); //print 6
        int[] prices4 = new int[]{4,4,7,6,1,1,4,2,5,6};
        System.out.println(maxProfit2(prices4)); //wrong print 7
        System.out.println(maxProfit3(prices4)); //print 8

    }

    // PS：只要今天比昨天价格高就交易，（并不符合逻辑。。。）
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if ((diff) > 0) {
                res += diff;
            }
        }
        return res;
    }

    // 开始思路：每次找到连续的递增子数组记录前后的差值，找到两个最大的。如下程序maxProfit2，其实是有问题，最大的差值，可能跨两个子数组的。
    // 使用前后遍历解决，如程序maxProfit3
    public static int maxProfit2(int[] prices) {
        if(prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int maxProfits = 0;
        int midProfits = 0;
        int index = 0;
        for (int i = 1; i < prices.length; i++) {
            if ((prices[i] - prices[i - 1]) < 0) {
                int diff = prices[i - 1] - prices[index];
                if (diff > maxProfits) {
                    midProfits = maxProfits;
                    maxProfits = diff;
                } else if (diff > midProfits) {
                    midProfits = diff;
                }
                index = i;
            } else if (i == (prices.length - 1)) {
                int diff = prices[i] - prices[index];
                if (diff > maxProfits) {
                    midProfits = maxProfits;
                    maxProfits = diff;
                } else if (diff > midProfits) {
                    midProfits = diff;
                }
            }
        }
        return maxProfits + midProfits;
    }

    // 从左往右是找更大的值，从右往左是找更小的值，将两次机会分在左右两边，这样单边的算法就很简单，就只有一次卖的机会
    // 而最后的结果就是两边的和中最大的那个
    public static int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int length = prices.length;
        int[] left = new int[length];
        int[] right = new int[length];
        //  left[i] 表示在 0 - i 中能够卖出的最大收益，当是0的时候表示不买也不卖
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < length; i++) {
            if (min <= prices[i]) {
                left[i] = Math.max(left[i - 1], prices[i] - min);
            } else {
                left[i] = left[i - 1];
                min = prices[i];
            }
        }
        // right[i] 表示在 i - A.length-1 中能够卖出的最大收益
        int max = prices[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            if (prices[i] <= max) {
                right[i] = Math.max(max - prices[i], right[i + 1]);
            } else {
                right[i] = right[i + 1];
                max = prices[i];
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            res = Math.max(res, left[i] + right[i]);
        }
        return res;
    }
}
