package lintcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/7
 * @Description 找出字符串数组中的乱序字符串
 */
public class DisorderString {
    public static void main(String[] args) throws Exception {
        String[] input = new String[]{"lint","intl","inlt","code", "", "", "cat", "tac"};
        List<String> res = anagrams(input);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    public static List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
        for (String str : strs) {
            String key = generateKey(str);
            if (hashMap.containsKey(key)) {
                hashMap.get(key).add(str);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                hashMap.put(key, list);
            }
        }
        for (ArrayList<String> list : hashMap.values()) {
            if (list.size() > 1) {
                for (String str : list) {
                    res.add(str);
                }
            }
        }
        return res;
    }

    private static String generateKey(String str) {
        StringBuilder sb = new StringBuilder();
        int[] hash = new int[26];
        for (int i = 0; i < str.length(); ++i) {
            int index = str.charAt(i) - 'a';
            hash[index]++;
        }
        for (int i = 0; i < 26; i++) {
            if (hash[i] != 0) {
                char c = (char) (i + 'a');
                sb.append(c);
                sb.append(hash[i]);
            }
        }
        return sb.toString();
    }
}
