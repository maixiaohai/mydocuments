package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/11
 * @Description  给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
 *               总共三种操作方法：插入一个字符,删除一个字符,替换一个字符
 *               思路：动态规划，与公共子序列不同的是，dp[i][j]代表的意义是当前的最小编辑距离
 *               动态规划方程：当i - 1、j - 1的字符相等时，dp[i][j] = dp[i - 1][j - 1]
 *               不相等时，有以下三种情况，取最小值
 *                    替换 dp[i - 1][j  - 1] + 1
 *                    插入 dp[i - 1][j] + 1
 *                    删除 dp[i][j - 1] + 1
 */
public class MinEditDistance {
    //给出 work1="mart" 和 work2="karma" 返回 3
    public static void main(String[] args) {
        String a = "sea";
        String b = "ate";
        System.out.println(minDistance(a, b));  //print 3
        a = "mart";
        b = "karma";
        System.out.println(minDistance(a, b));
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            if (word2 != null) {
                return word2.length();
            } else {
                return 0;
            }
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // init
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
