package lintcode.hash;

import java.util.HashMap;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/23
 * @Description  给定一个字符串，请找出其中无重复字符的最长子字符串 O(n)时间
 */
public class LongestSubstring {
    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(solution1(s));
        System.out.println(solution2(s));
    }

    // 思路2, 把思路1中的第二层循环提炼到第一层完成, 用start变量记录
    // 测试数据用时： 3074 ms
    public static int solution2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int max = 0;
        int start = 0;
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < length; i++) {
            if (hm.containsKey(s.charAt(i))) {
                if (hm.get(s.charAt(i)) >= start) { //以防查到的重复字符是在start标记位置前面的
                    max = Math.max(max, i - start);
                    start = hm.get(s.charAt(i)) + 1;
                }
            } else if (i == (length - 1)) {
                max = Math.max(max, i - start + 1);
            }
            hm.put(s.charAt(i), i);
        }
        return max;
    }

    //思路1 硬算
    // 测试数据用时 ： 3175 ms
    public static int solution1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int max = 0;
        int i = 0;
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        while (i < length) {
            if (max >= (length - i)) {
                break;
            }
            hm.put(s.charAt(i), i);
            for (int j = i + 1; j < length; j++) {
                if (hm.containsKey(s.charAt(j))) {
                    max = Math.max(max, j - i);
                    i = hm.get(s.charAt(j)) + 1;
                    break;
                } else if (j == (length - 1)) {
                    max = Math.max(max, j - i + 1);
                    i = j + 1;
                    break;
                } else {
                    hm.put(s.charAt(j), j);
                }
            }
            hm.clear();
        }
        return max;
    }
}
