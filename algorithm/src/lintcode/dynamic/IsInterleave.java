package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/11
 * @Description  给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
 *               dp[i][j]意味着s1的前0-i个字符和s2的前0-j个字符能否交叉构成s3的0-(i+j)个字符
 *               状态转移：两个方向来，[i][j - 1]=>加上s2的j字符,[i - 1][j]=>加上s1的字符
 */
public class IsInterleave {
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        if (isInterleave(s1, s2, s3)) {
            System.out.println("yes~~ " + s3);
        }
        s3 = "aadbbbaccc";
        if (isInterleave(s1, s2, s3)) {
            System.out.println("yes~~ " + s3);
        }
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != (s1.length() + s2.length())) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        //init
        for (int i = 1; i <= m; i++) {
             if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                 dp[i][0] = true;
             }
        }
        for (int j = 1; j <= n; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = true;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if ((s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) ||
                        (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }
}
