package lintcode.DFSANDBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/12
 * @Description 给一个不包含01的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。
 */
public class MobilePhone {
    // input "23"
    // output ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    // 2 => a b c 3=> d e f 4=> g h i 5=> j k l 6=> m n o 7=> p q r s 8=> t u v 9=> w x y z

    public static void main(String[] args) {
        String input = "23";
        ArrayList<String> res = letterCombinations(input);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    public static ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if (digits == null || "".equals(digits)) {
            return res;
        }
        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[]{});
        map.put('1', new char[]{});
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        StringBuilder sb = new StringBuilder();
        letterCombinations(digits, sb, res, map);
        return res;
    }

    private static void letterCombinations(String digits, StringBuilder sb,
                                    ArrayList<String> res, Map<Character, char[]> map) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (Character c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            letterCombinations(digits, sb, res, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
