package lintcode.dynamic;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/11
 * @Description  给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。
 *               思路：dp[i]表示字符串s[0,i]是否是可分割单词,是则为true，否为false。
 *               dp[j]  = dp[i] &&( s[i+1 ,j]在dict中 )
 */
public class WordBreak {
    public static void main(String[] args) {
        //s = "xiaowanzi" dict = ["xiao","wanzi"] 返回 true
        String s = "xiaowanzi";
        Set<String> dict = new HashSet<String>();
        dict.add("xiao");
        dict.add("wanzi");
        System.out.println(wordBreak(s, dict));
    }

    public static boolean wordBreak(String s, Set<String> dict) {
        if ((s == null || s.length() == 0) && (dict == null || dict.size() == 0)) {
            return true;
        }
        int l = s.length();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;
        for (int i = 0; i <= l; i++) {
            if (!dp[i]) {
                continue;
            }
            for (String word : dict) {
                int length = word.length();
                int end = length + i;
                if (end > l) {
                    continue;
                }
                if (s.substring(i, end).equals(word)) {
                    dp[end] = true;
                }
            }
        }
        return dp[l];
    }
}
