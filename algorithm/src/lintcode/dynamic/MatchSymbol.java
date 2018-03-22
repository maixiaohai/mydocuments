package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/2
 * @Description  判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：
 *               '?' 可以匹配任何单个字符。'*' 可以匹配任意字符串（包括空字符串）。
 *               两个串完全匹配才算匹配成功。
 *               思路：动态规划，match[i][j] : 表示从i到s.length,从j到p.length的是否匹配
 */
public class MatchSymbol {
    public static void main(String[] args) {
        String s = "aa";
        String p = "a*a";
        if (isMatch(s, p)) {
            System.out.println(s + " is match with " + p);
        }
    }

    public static boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[s.length()][p.length()]=true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*') {
                System.out.println("not meet *");
                break;
            } else {
                System.out.println("meet *");
                match[s.length()][i] = true;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    match[i][j] = match[i + 1][j + 1];
                    System.out.println("step 1 : " + i + " " + j);
                } else if (p.charAt(j) == '*') {
                    match[i][j] = match[i+1][j] || match[i][j+1] || match[i+1][j+1];
                    System.out.println("step 2 : " + i + " " + j);
                } else {
                    System.out.println("step 3 : " + i + " " + j);
                    match[i][j] = false;
                }
            }
        }
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < p.length() + 1; j++) {
                System.out.print(match[i][j] + " ");
            }
            System.out.println();
        }
        return match[0][0];
    }
}
