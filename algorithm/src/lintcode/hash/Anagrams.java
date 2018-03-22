package lintcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/22
 * @Description  Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
                 Strings consists of lowercase English letters only and the length of both strings s and p
                 will not be larger than 40,000.
 */
public class Anagrams {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res = findAnagrams(s, p);
        for (Integer x : res) {
            System.out.println(x);
        }
        List<Integer> res2 = findAnagrams2(s, p);
        for (Integer x : res2) {
            System.out.println(x);
        }
    }

    public static List<Integer> findAnagrams(String s, String p) {
        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);
        p = new String(pArray);
        List<Integer> res = new ArrayList<Integer>();
        if (s == null || s.length() < p.length()) {
            return res;
        }
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (p.indexOf(s.charAt(i)) != -1) {
                String x = s.substring(i, i + p.length());
                char[] xArray = x.toCharArray();
                Arrays.sort(xArray);
                x = new String(xArray);
                if (x.equals(p)) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    public static List<Integer> findAnagrams2(String s, String p) {
        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);
        p = new String(pArray);
        List<Integer> res = new ArrayList<Integer>();
        if (s == null || s.length() < p.length()) {
            return res;
        }
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (p.indexOf(s.charAt(i)) != -1) {
                String x = s.substring(i, i + p.length());
                if (isAnagrams(x, p)) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    private static boolean isAnagrams(String x, String p) {
        int[] record = new int[26];
        for (int i = 0; i < 26; i++) {
            record[i] = 0;
        }
        for (int i = 0; i < x.length(); i++) {
            record[x.charAt(i) - 'a']++;
            record[p.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
