package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/10
 * @Description 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String a = "ABCD";
        String b = "EDCA";
        System.out.println(longestCommonSubsequence(a, b)); //LCS是 "A" (或 D或C)，返回1
        b = "EACB";
        System.out.println(longestCommonSubsequence(a, b)); // LCS是"AC"返回2
    }

    public static int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null) {
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char a = A.charAt(i - 1);
                char b = B.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j]+",");
            }
            System.out.println("");
        }
        int max_length = dp[m][n];
        char[] comStr = new char[max_length];
        int i = m;
        int j = n;
        while (max_length != 0) {
            if (dp[i][j] != dp[i - 1][j - 1]) {
                if (dp[i - 1][j] == dp[i][j - 1]) { //两字符相等，为公共字符
                    comStr[max_length - 1] = A.charAt(i - 1);
                    max_length--;
                    i--;
                    j--;
                } else {//取两者中较长者作为A和B的最长公共子序列
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        i--;
                    } else {
                        j--;
                    }
                }
            } else {
                i--;
                j--;
            }
        }
        System.out.println(comStr);
        return dp[m][n];
    }
}
