package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/5
 * @Description  给出字符串S和字符串T，计算S的不同的子序列中T出现的个数。
 *               子序列字符串是原始字符串通过删除一些(或零个)产生的一个新的字符串，
 *               并且对剩下的字符的相对位置没有影响。(比如，“ACE”是“ABCDE”的子序列字符串,而“AEC”不是)。
 *               思路：DFS会超时，使用dp算法，f(i - 1, j - 1)表示 s中前i - 1个字符中，t的前j - 1个字符出现的次数
 *               分析：当s.charAt(i) != t.charAt(j)时，f(i, j) = f(i - 1, j)
 *               当相等时， f(i, j) = f(i - 1, j) + f(i - 1, j - 1)
 */
public class NumDistinct {
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(numDistinct(s, t));
    }

    public static int numDistinct(String s, String t) {
        if (s == null || t ==null) {
            return 0;
        }
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        System.out.println("m " + m + " n " + n);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
