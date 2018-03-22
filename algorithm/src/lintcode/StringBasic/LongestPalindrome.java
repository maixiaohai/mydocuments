package lintcode.StringBasic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/4
 * @Description 给出一个字符串（假设长度最长为1000），求出它的最长回文子串，你可以假定只有一个满足条件的最长回文串。
 *              思路：O(n*n)的时间复杂度很好解，O(n)只有Manacher算法能达到
 *              方法一：需要引入一个二维数组M[i][i]来记录字符串与反转后的字符串的重复状态
 *              然后只需要找到M[i][i]上最长的连续1的个数就是最长回文字符串的长度，记住位置，再在原来的字符串上截取即可。
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        //给出字符串 "abcdzdcab"，它的最长回文子串为 "cdzdc"
        String str = "abcdzdcab";
        System.out.println(longestPalindrome(str));
        System.out.println(longestPalindrome2(str));
        String str2 = "abcdzzzdcbe";
        System.out.println(longestPalindrome(str2));
        System.out.println(longestPalindrome2(str2));
    }

    public static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int l = s.length() - 1;
        int[][] equal = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    equal[i][l - j] = 1;
                }
            }
        }
        int start = 0;
        int end = 0;
        int maxL = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i <= l; i++) {
            if (equal[i][i] == 1) {
                if ((end - start) > maxL) {
                    maxL = end - start;
                    left = start;
                    right = end;
                }
                end++;
            } else {
                start = i;
                end = i;
            }
        }
        return s.substring(left + 1, right + 2);
    }

    // 方法二 Manacher算法
    //preprocess : 求回文串时需要判断其奇偶性，也就是求aba 和abba 的算法略有差距。preprocess方法将aba->$#a#b#a#@，统一了算法
    //然后是算法的核心：用一个辅助数组p记录以每个字符为中心(包含这个字符)的最长回文半径，也就是p[i]记录以str[i]字符为中心的最长回文串半径。
    //               p[i]最小为1，此时回文串为str[i]本身
    //参考文章：https://segmentfault.com/a/1190000003914228
    private static int[] p;  //存最长回文串半径的数组
    private static String s; //原字符串
    private static char[] t; //处理后的字符数组

    public static String longestPalindrome2(String str) {
        s = str;
        preprocess();
        p = new int[t.length];
        int mid = 0; // right对应的回文串的对称轴所在的位置
        int maxRight = 0; //表示当前访问到的所有回文子串，所能触及的最右一个字符的位置
        for (int i = 1; i < t.length - 1; i++) {
            //当前考查的i在maxRight的左边，有一部分是不需要再计算的;
            // i以mid为中心的对称mirror的p值 或 当p[mirror]的范围大于最右边界时，以maxRight-i为初始值
            if (maxRight > i) {
                int mirror = 2 * mid - i;
                p[i] = Math.min(maxRight - i, p[mirror]);
            }
            //扩展回文串
            while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) {
                p[i]++;
            }
            //更新最右边界和其对应的轴心
            if (i + p[i] > maxRight) {
                mid = i;
                maxRight = i + p[i];
            }
        }
        int maxLength = 0;
        int center = 0;
        for (int i = 1; i < p.length - 1; i++) {
            if (p[i] > maxLength) {
                maxLength = p[i];
                center = i;
            }
        }
        return s.substring((center - 1 - maxLength) / 2, (center - 1 + maxLength) / 2);
    }

    private static void preprocess() {
        t = new char[s.length() * 2 + 3];
        t[0] = '$';
        t[s.length() * 2 + 2] = '@';
        for (int i = 0; i < s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[s.length() * 2 + 1] = '#';
    }
}
